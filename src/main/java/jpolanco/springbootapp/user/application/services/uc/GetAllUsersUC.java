package jpolanco.springbootapp.user.application.services.uc;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.domain.Command;
import jpolanco.springbootapp.shared.domain.CommandVoid;
import jpolanco.springbootapp.user.application.errors.UserAppError;

import jpolanco.springbootapp.user.application.ports.output.ReturnUserCommand;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;

import jpolanco.springbootapp.user.domain.model.valueobjects.Role;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllUsersUC implements CommandVoid<List<ReturnUserCommand>> {
    private final UserRepositoryPort userRepository;

    @Override
    public Result<List<ReturnUserCommand>> execute() {
        var users = userRepository.findAll();
        if (users.isEmpty()) {
            return Result.failure(UserAppError.EMPTY_LIST);
        }
        var userCommands = users.stream()
                .map(user -> new ReturnUserCommand(
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
                        user.getCreatedAt())
                ).toList();
        return Result.success(userCommands);
    }
}
