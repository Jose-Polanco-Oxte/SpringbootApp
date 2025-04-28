package jpolanco.springbootapp.user.application.services.uc;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.domain.Command;
import jpolanco.springbootapp.user.application.errors.UserAppError;
import jpolanco.springbootapp.user.application.ports.input.EncoderService;
import jpolanco.springbootapp.user.application.ports.input.QRService;
import jpolanco.springbootapp.user.application.ports.input.holders.CreateHolder;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;

import jpolanco.springbootapp.user.application.services.UserFactory;
import jpolanco.springbootapp.user.application.services.UserValidation;
import jpolanco.springbootapp.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUC implements Command<User, CreateHolder> {
    private final UserRepositoryPort persistencePort;
    private final QRService qrService;
    private final UserValidation userValidation;
    private final UserFactory userFactory;
    @Override
    public Result<User> execute(CreateHolder request) {
        if (userValidation.isEmailExists(request.email())) {
            return Result.failure(UserAppError.USER_ALREADY_EXISTS);
        }
        var result = userFactory.create(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password()
        );
        if (result.isFailure()) {
            return Result.failure(result.getError());
        }
        var user = result.getValue();
        qrService.generate(user.getQRFileName(), user.getEmail());
        var userSaved = persistencePort.save(user);
        return Result.success(userSaved);
    }
}
