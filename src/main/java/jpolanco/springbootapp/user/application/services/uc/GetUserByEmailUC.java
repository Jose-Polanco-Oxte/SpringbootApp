package jpolanco.springbootapp.user.application.services.uc;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.domain.Command;
import jpolanco.springbootapp.user.application.errors.UserAppError;
import jpolanco.springbootapp.user.application.ports.output.ReturnUserCommand;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;

import jpolanco.springbootapp.user.domain.model.User;
import jpolanco.springbootapp.user.domain.model.valueobjects.Role;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetUserByEmailUC implements Command<ReturnUserCommand, String> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Result<ReturnUserCommand> execute(String email) {
        var maybeUser = userRepositoryPort.findByEmail(email);
        return getReturnUserCommandResult(maybeUser);
    }

    static Result<ReturnUserCommand> getReturnUserCommandResult(Optional<User> maybeUser) {
        if (maybeUser.isEmpty()) {
            return Result.failure(UserAppError.USER_NOT_FOUND);
        }
        var user = maybeUser.get();
        return Result.success(new ReturnUserCommand(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getQRFileName(),
                user.getStatus(),
                user.getRoles()
                        .stream()
                        .map(Role::getValue)
                        .toList(),
                user.getCreatedAt()
        ));
    }
}
