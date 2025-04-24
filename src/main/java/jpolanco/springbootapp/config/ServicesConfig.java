package jpolanco.springbootapp.config;

import jpolanco.springbootapp.user.application.ports.input.EncoderService;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;
import jpolanco.springbootapp.user.application.services.QRService;
import jpolanco.springbootapp.user.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServicesConfig {
    @Bean
    public UserService userService(UserRepositoryPort userRepository, EncoderService encoderService, QRService qrService) {
        return new UserService(userRepository, encoderService, qrService);
    }
}
