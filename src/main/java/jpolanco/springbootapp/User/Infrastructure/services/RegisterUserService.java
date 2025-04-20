package jpolanco.springbootapp.User.Infrastructure.services;

import jpolanco.springbootapp.Shared.Domain.valueobjects.Email;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Name;
import jpolanco.springbootapp.Shared.Domain.valueobjects.QR;
import jpolanco.springbootapp.User.Application.RegistrationUseCase;
import jpolanco.springbootapp.User.Domain.User;
import jpolanco.springbootapp.User.Infrastructure.dto.RegisterDTO;
import jpolanco.springbootapp.User.Infrastructure.repository.UserRepositoryImplementation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {
    private final UserRepositoryImplementation userRepository;
    private final ZxingQRService createQR;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserService(UserRepositoryImplementation userRepository, ZxingQRService createQR, PasswordEncoder encryptionManager) {
        this.userRepository = userRepository;
        this.createQR = createQR;
        this.passwordEncoder = encryptionManager;
    }

    public void registerUser(RegisterDTO registerDTO) {
        RegistrationUseCase registrationUseCase = new RegistrationUseCase(userRepository);
        registrationUseCase.execute(mapToUser(registerDTO));
    }

    private User mapToUser(RegisterDTO registerDTO) {
        if (createQR.existsQRcode(registerDTO.getEmail())) {
            throw new IllegalArgumentException("QR code already exists");
        }
        User user = new User();
        user.setEmail(new Email(registerDTO.getEmail()));
        user.setName(new Name(registerDTO.getFirstName(), registerDTO.getLastName()));
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setQr(new QR(createQR.generateQRcode(registerDTO.getEmail())));
        return user;
    }
}
