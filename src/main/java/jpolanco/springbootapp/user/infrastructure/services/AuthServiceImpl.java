package jpolanco.springbootapp.user.infrastructure.services;

import jpolanco.springbootapp.shared.application.Dto;
import jpolanco.springbootapp.shared.domain.Result;
import jpolanco.springbootapp.user.application.errors.UserAppError;
import jpolanco.springbootapp.user.application.ports.input.JwtProvider;
import jpolanco.springbootapp.user.application.uc.CreateUserUc;
import jpolanco.springbootapp.user.application.uc.GetUserByEmailUC;
import jpolanco.springbootapp.user.application.uc.LoginUC;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.LoginRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.RegisterRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.response.UserTokenResponse;
import jpolanco.springbootapp.user.infrastructure.components.AuxTokenManagerImpl;
import jpolanco.springbootapp.user.infrastructure.publisher.DomainEventsPublisher;
import jpolanco.springbootapp.user.infrastructure.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final LoginUC loginUC;
    private final CreateUserUc createUserUc;
    private final GetUserByEmailUC getUserByEmailUC;
    private final JwtProvider jwtService;
    private final AuthenticationManager authentication;
    private final AuxTokenManagerImpl auxTokenManagerImpl;
    private final DomainEventsPublisher publisher;

    @Transactional
    @Override
    public Result<Dto> login(LoginRequest request) {
        var verifiedUser = loginUC.login(request.email(), request.password());
        if (verifiedUser.isFailure()) {
            return Result.failure(verifiedUser.getError());
        }
        var user = verifiedUser.getValue();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        auxTokenManagerImpl.saveToken(user, accessToken);
        authentication.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        request.password()
                )
        );
        return Result.success(new UserTokenResponse(accessToken, refreshToken));
    }

    @Transactional
    @Override
    public Result<Dto> register(RegisterRequest request) {
        var createdUser = createUserUc.create(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password()
        );

        if (createdUser.isFailure()) {
            return Result.failure(createdUser.getError());

        }
        var user = createdUser.getValue();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        auxTokenManagerImpl.saveToken(user, accessToken);
        user.pullEvents().forEach(publisher::publish);
        user.clearEvents();
        return Result.success(new UserTokenResponse(accessToken, refreshToken));
    }

    @Transactional
    @Override
    public Result<Dto> refresh(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.failure(UserAppError.INVALID_HEADER);
        }
        var refreshToken = authHeader.substring(7);
        var userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return Result.failure(UserAppError.INVALID_TOKEN);
        }
        var maybeUser = getUserByEmailUC.get(userEmail);
        if (maybeUser.isFailure()) {
            return Result.failure(maybeUser.getError());
        }
        var user = maybeUser.getValue();
        if (!jwtService.isTokenValid(refreshToken, user.getEmail())) {
            return Result.failure(UserAppError.INVALID_TOKEN);
        }
        var accessToken = jwtService.generateAccessToken(user);
        auxTokenManagerImpl.revokeAllUserTokens(user.getEmail());
        auxTokenManagerImpl.saveToken(user, accessToken);
        return Result.success(new UserTokenResponse(accessToken, refreshToken));
    }
}
