package jpolanco.springbootapp.user.application.services;

import jpolanco.springbootapp.shared.application.FactoryService;
import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.application.ResultBuilder;
import jpolanco.springbootapp.user.application.ports.input.EncoderService;
import jpolanco.springbootapp.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFactory {
    private final EncoderService encoderService;

    public Result<User> create(String firstName, String lastName, String email, String password) {
        var result = User.create(
                firstName,
                lastName,
                email,
                encoderService.encode(password)
        );
        if (result.isFailure()) {
            return Result.failure(result.getError());
        }
        var user = result.getValue();
        return Result.success(user);
    }

    public Result<User> update(String firstName, String lastName, String email, User user) {
        var updateResult = ResultBuilder
                .from(user.changeName(firstName, lastName))
                .then(result -> user.changeEmail(email))
                .then(result -> user.newQRFileName())
                .result();
        if (updateResult.isFailure()) {
            return Result.failure(updateResult.getError());
        }
        return Result.success(user);
    }
}
