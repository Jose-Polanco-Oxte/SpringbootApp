package jpolanco.springbootapp.config;

import jpolanco.springbootapp.user.application.ports.input.EncoderService;
import jpolanco.springbootapp.user.application.ports.input.QRService;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;
import jpolanco.springbootapp.user.application.services.UserFactory;
import jpolanco.springbootapp.user.application.services.UserValidation;
import jpolanco.springbootapp.user.application.services.uc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserServicesConfig {

    @Bean
    public UserValidation userValidation(UserRepositoryPort userRepository) {
        return new UserValidation(userRepository);
    }

    @Bean
    public UserFactory userFactory(EncoderService encoderService) {
        return new UserFactory(encoderService);
    }

    @Bean
    public CreateUserUC createUserUC(UserRepositoryPort userRepository, QRService qrService, UserValidation userValidation, UserFactory userFactory) {
        return new CreateUserUC(userRepository, qrService, userValidation, userFactory);
    }
    @Bean
    public UpdateUserByIdUC updateUserUC(UserRepositoryPort userRepository, QRService qrService, UserValidation userValidation, UserFactory userFactory) {
        return new UpdateUserByIdUC(userRepository, qrService, userValidation, userFactory);
    }

    @Bean
    public GetUserByIdUC getUserUC(UserRepositoryPort userRepository) {
        return new GetUserByIdUC(userRepository);
    }

    @Bean
    public DeleteUserByIdUC deleteUserUC(UserRepositoryPort userRepository, QRService qrService) {
        return new DeleteUserByIdUC(userRepository, qrService);
    }

    @Bean
    public GetAllUsersUC getAllUsersUC(UserRepositoryPort userRepository) {
        return new GetAllUsersUC(userRepository);
    }

    @Bean
    public GetUserByEmailUC getUserByEmailUC(UserRepositoryPort userRepository) {
        return new GetUserByEmailUC(userRepository);
    }

    @Bean
    public LoginUC loginUC(UserRepositoryPort userRepository, PasswordEncoder encoderService) {
        return new LoginUC(userRepository, encoderService);
    }
}
