package jpolanco.springbootapp.user.application.services;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.user.application.errors.UserAppError;
import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;
import jpolanco.springbootapp.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidation {
    private final UserRepositoryPort userRepository;

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Result<User> onUpdateEmailIsValid(String email, String userId) {
        var OptionalUser = userRepository.findById(userId);
        if (OptionalUser.isEmpty()) {
            return Result.failure(UserAppError.USER_NOT_FOUND);
        }
        var user = OptionalUser.get();
        if (isEmailExists(email) && !user.getEmail().equals(email)) {
            return Result.failure(UserAppError.EMAIL_IS_ALREADY_IN_USE);
        }
        return Result.success(user);
    }
}
