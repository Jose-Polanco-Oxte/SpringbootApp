package jpolanco.springbootapp.User.Application;

import jpolanco.springbootapp.Shared.Application.MappeableCommand;
import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.Shared.Domain.patterns.Result;
import jpolanco.springbootapp.User.Application.exceptions.RegistrationFailure;
import jpolanco.springbootapp.User.Domain.User;
import jpolanco.springbootapp.User.Domain.repositories.UserRepository;

public class RegistrationUseCase {
    private final UserRepository userRepository;

    public RegistrationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result<User> execute(MappeableCommand<Result<User>> registerCommand) {
        Result<User> result = registerCommand.toDomain();
        if (result.isSuccess()) {
            User user = result.getValue();
            userRepository.findByEmail(user.getEmail().getValue())
                    .ifPresent(existingUser -> {
                        throw new RegistrationFailure(
                                "User already exists",
                                new ExceptionDetails("User already exists", "ERROR")
                        );
                    });

            userRepository.save(user);
        } else {
            return Result.failure(result.getError(), result.getUserFriendlyMessage());
        }
        return result;
    }
}
