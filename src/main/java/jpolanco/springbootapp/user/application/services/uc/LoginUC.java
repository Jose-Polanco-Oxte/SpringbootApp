package jpolanco.springbootapp.user.application.services.uc;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.domain.Command;
import jpolanco.springbootapp.user.application.errors.UserAppError;
import jpolanco.springbootapp.user.application.ports.input.holders.LoginHolder;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;
import jpolanco.springbootapp.user.domain.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUC implements Command<User, LoginHolder> {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder encoderService;

    public LoginUC(UserRepositoryPort userRepositoryPort, @Qualifier("passwordEncoder") PasswordEncoder encoderService) {
        this.userRepositoryPort = userRepositoryPort;
        this.encoderService = encoderService;
    }
    @Override
    public Result<User> execute(LoginHolder loginRequest) {
        var maybeUser = userRepositoryPort.findByEmail(loginRequest.email());
        if (maybeUser.isEmpty()) {
            return Result.failure(UserAppError.USER_NOT_FOUND);
        }
        var user = maybeUser.get();
        if (!user.isActive()) {
            return Result.failure(UserAppError.USER_NOT_ACTIVE);
        }
        if (!encoderService.matches(loginRequest.password(), user.getEncodedPassword())) {
            return Result.failure(UserAppError.USER_NOT_AUTHORIZED);
        }
        return Result.success(user);
    }
}
