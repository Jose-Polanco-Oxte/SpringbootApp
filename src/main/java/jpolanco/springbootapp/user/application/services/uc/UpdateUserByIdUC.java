package jpolanco.springbootapp.user.application.services.uc;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.domain.Command;
import jpolanco.springbootapp.user.application.ports.input.QRService;
import jpolanco.springbootapp.user.application.ports.input.holders.UserHolder;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;
import jpolanco.springbootapp.user.application.services.UserFactory;
import jpolanco.springbootapp.user.application.services.UserValidation;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UpdateUserByIdUC implements Command<Void, UserHolder> {

    private final UserRepositoryPort persistencePort;
    private final QRService qrService;
    private final UserValidation userValidation;
    private final UserFactory userFactory;


    @Override
    public Result<Void> execute(UserHolder request) {
        var maybeUser = userValidation.onUpdateEmailIsValid(request.email(), request.id());
        if (maybeUser.isFailure()) {
            return Result.failure(maybeUser.getError());
        }
        var user = maybeUser.getValue();
        String OldQrFileName = user.getQRFileName();
        var updateResult = userFactory.update(
                request.firstName(),
                request.lastName(),
                request.email(),
                user
        );
        if (updateResult.isFailure()) {
            return Result.failure(updateResult.getError());
        }
        qrService.delete(OldQrFileName);
        qrService.generate(user.getQRFileName(), user.getEmail());
        persistencePort.save(user);
        return Result.success();
    }
}
