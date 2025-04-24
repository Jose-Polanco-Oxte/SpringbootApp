package jpolanco.springbootapp.user.application.services;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.user.application.exceptions.ServiceError;
import jpolanco.springbootapp.user.application.ports.input.EncoderService;
import jpolanco.springbootapp.user.application.ports.input.UserServicePort;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;
import jpolanco.springbootapp.user.domain.model.User;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserRepositoryPort persistencePort;
    private final EncoderService encoderService;
    private final QRService qrService;


    @Override
    public Result<String> create(RegisterCommand command) {
        if (persistencePort.findByEmail(command.email()).isPresent()) {
            return Result.failure(ServiceError.USER_ALREADY_EXISTS);
        }
        System.out.println("Creating user: " + command.firstName() + " " + command.lastName());
        var result = User.create(
                command.firstName(),
                command.lastName(),
                command.email(),
                encoderService.encode(command.password())
        );
        System.out.println("User created: " + result.getValue().getEmail());
        if (result.isFailure()) {
            return Result.failure(result.getError());
        }
        var user = result.getValue();
        System.out.println("User created value: " + user.getEmail());
        if (qrService.exist(user.getEmail())) {
            return Result.failure(ServiceError.QR_CODE_EXISTS);
        }
        qrService.generate(user.getQRFileName(), user.getEmail());
        System.out.println("Id: " + user.getId());
        var savedUser = persistencePort.save(user);
        return Result.success(savedUser.getId());
    }

    @Override
    public Result<String> update(UserCommand command) {
        return null;
    }

    @Override
    public Result<String> deleteById(String userId) {
        return null;
    }

    @Override
    public Result<String> getById(String userId) {
        return null;
    }

    @Override
    public Result<List<User>> getAll() {
        return null;
    }
}
