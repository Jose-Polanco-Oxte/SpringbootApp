package jpolanco.springbootapp.user.infrastructure.services;

import jpolanco.springbootapp.user.application.ports.input.holders.CreateHolder;
import jpolanco.springbootapp.user.application.ports.input.holders.LoginHolder;
import jpolanco.springbootapp.user.application.services.UserFactory;
import jpolanco.springbootapp.user.application.services.uc.CreateUserUC;
import jpolanco.springbootapp.user.application.services.uc.GetUserByEmailUC;
import jpolanco.springbootapp.user.application.services.uc.LoginUC;
import jpolanco.springbootapp.user.domain.model.User;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.LoginRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.RegisterRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.TokenResponseDto;
import jpolanco.springbootapp.user.infrastructure.adapters.output.Mapper;
import jpolanco.springbootapp.user.infrastructure.adapters.output.mysql.UserRepositoryMySQL;
import jpolanco.springbootapp.user.infrastructure.adapters.output.persistence.Token;
import jpolanco.springbootapp.user.infrastructure.adapters.output.repository.JpaTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthServices {
    private final LoginUC loginUC;
    private final CreateUserUC createUserUC;
    private final JpaTokenRepository jpaTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authentication;
    private final UserRepositoryMySQL userRepository;

    public TokenResponseDto register(RegisterRequest registerRequest) {
        var result = createUserUC.execute(
                new CreateHolder(
                        registerRequest.firstName(),
                        registerRequest.lastName(),
                        registerRequest.email(),
                        registerRequest.password()
                ));

        if (result.isFailure()) {
            throw new AuthenticationServiceException(result.getMessage());
        }

        var user = result.getValue();
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveToken(user, accessToken);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    public TokenResponseDto login(LoginRequest loginRequest) {
        var result = loginUC.execute(new LoginHolder(loginRequest.email(), loginRequest.password()));
        if (result.isFailure()) {
            throw new AuthenticationServiceException(result.getMessage(), null);
        }
        System.out.println("LoginUC result: " + result.getValue().getEmail());
        authentication.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );
        var user = result.getValue();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveToken(user, jwtToken);
        return new TokenResponseDto(jwtToken, refreshToken);
    }

    public TokenResponseDto refresh(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AuthenticationServiceException("Invalid Bearer accessToken");
        }
        var refreshToken = authHeader.substring(7);
        var userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            throw new AuthenticationServiceException("Invalid Refresh accessToken");
        }
        var maybeUser = userRepository.findByEmail(userEmail);
        if (maybeUser.isEmpty()) {
            throw new AuthenticationServiceException("User not found");
        }
        var user = maybeUser.get();
        if (!jwtService.isTokenValid(refreshToken, user.getEmail())) {
            throw new AuthenticationServiceException("Invalid Refresh accessToken");
        }
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveToken(user, accessToken);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    private void saveToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(Mapper.toEntity(user))
                .token(jwtToken)
                .type(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        jpaTokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = jpaTokenRepository.findAllValidIsFalseOrRevokedIsFalseByUserId(UUID.fromString(user.getId()));
        if (!validUserTokens.isEmpty()) {
            for (Token token : validUserTokens) {
                token.setExpired(true);
                token.setRevoked(true);
            }
        }
        jpaTokenRepository.saveAll(validUserTokens);
    }
}
