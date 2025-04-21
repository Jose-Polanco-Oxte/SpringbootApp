package jpolanco.springbootapp.User.Infrastructure.services;

import jpolanco.springbootapp.Shared.Domain.patterns.Result;
import jpolanco.springbootapp.User.Application.RegisterCommand;
import jpolanco.springbootapp.User.Application.RegistrationUseCase;
import jpolanco.springbootapp.User.Domain.User;
import jpolanco.springbootapp.User.Infrastructure.dto.RegisterDTO;
import jpolanco.springbootapp.User.Infrastructure.repository.UserRepositoryImplementation;
import jpolanco.springbootapp.User.Infrastructure.validators.PasswordValidator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    private final UserRepositoryImplementation userRepository;
    private final Environment environment;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepositoryImplementation userRepository,
                             Environment environment, ApplicationEventPublisher eventPublisher,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.environment = environment;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = passwordEncoder;
    }

    public Result<User> createUser(RegisterDTO registerDTO) {

        RegistrationUseCase registrationUseCase = new RegistrationUseCase(userRepository);

        String pathQR = environment.getProperty("QRCODESPATH") + "\\" + registerDTO.getEmail().split("@")[0] + ".png";

        RegisterCommand registerCommand = new RegisterCommand(
                registerDTO.getEmail(),
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                passwordEncoder.encode(registerDTO.getPassword()),
                pathQR
        );

        Result<User> result = registrationUseCase.execute(registerCommand);
        if (result.isSuccess()) {
            UserCreatedEvent userCreatedEvent = new UserCreatedEvent(registerDTO.getEmail());
            eventPublisher.publishEvent(userCreatedEvent);
        }
        return result;
    }
}
