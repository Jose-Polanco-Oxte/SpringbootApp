package jpolanco.springbootapp.user.application.services.uc;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.domain.Command;
import jpolanco.springbootapp.user.application.errors.UserAppError;
import jpolanco.springbootapp.user.application.ports.input.QRService;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserByIdUC implements Command<Void, String> {

    private final UserRepositoryPort userRepository;
    private final QRService qrService;

    @Override
    public Result<Void> execute(String userId) {
        var maybeUser = userRepository.findById(userId);
        if (maybeUser.isEmpty()) {
            return Result.failure(UserAppError.USER_NOT_FOUND);
        }
        var user = maybeUser.get();
        qrService.delete(user.getQRFileName());
        userRepository.deleteById(userId);
        return Result.success();
    }
}
