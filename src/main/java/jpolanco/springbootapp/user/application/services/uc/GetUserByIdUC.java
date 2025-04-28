package jpolanco.springbootapp.user.application.services.uc;

import jpolanco.springbootapp.shared.application.Result;

import jpolanco.springbootapp.shared.domain.Command;
import jpolanco.springbootapp.user.application.ports.output.ReturnUserCommand;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

import static jpolanco.springbootapp.user.application.services.uc.GetUserByEmailUC.getReturnUserCommandResult;

@RequiredArgsConstructor
public class GetUserByIdUC implements Command<ReturnUserCommand, String> {

    private final UserRepositoryPort userRepository;
    @Override
    public Result<ReturnUserCommand> execute(String userId) {
        var maybeUser = userRepository.findById(userId);
        return getReturnUserCommandResult(maybeUser);
    }
}
