package jpolanco.springbootapp.User.Application;

import jpolanco.springbootapp.Shared.Domain.valueobjects.Email;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Name;
import jpolanco.springbootapp.Shared.Domain.valueobjects.QR;
import jpolanco.springbootapp.User.Domain.User;
import jpolanco.springbootapp.User.Domain.UserRepository;

public class RegistrationUseCase {
    private final UserRepository userRepository;

    public RegistrationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {
        if (userRepository.existByEmail(user.getEmail().getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        userRepository.save(user);
    }

    public static void main(String[] args) {
        //Test

        UserRepository userRepository = new UserRepository() {
            @Override
            public void save(User user) {
                System.out.println("User saved: " + user.getEmail().getEmail() + " " + user.getName().toString());
            }

            @Override
            public User findById(Long aLong) {
                return null;
            }

            @Override
            public void update(User entity) {

            }

            @Override
            public void delete(Long aLong) {

            }

            @Override
            public boolean existByEmail(String email) {
                return false; // Simulate that the email does not exist
            }
        };

        RegistrationUseCase registrationUseCase = new RegistrationUseCase(userRepository);
        Name name = new Name("John", "Doe");
        Email email = new Email("test@gmail.com");
        QR qr = new QR("w");
        User user = new User(3L, email, name, "passworWd123", qr);
        try {
            registrationUseCase.execute(user);
            System.out.println("User registered successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
