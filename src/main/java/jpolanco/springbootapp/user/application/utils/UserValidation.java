package jpolanco.springbootapp.user.application.utils;

import jpolanco.springbootapp.shared.domain.Result;
import jpolanco.springbootapp.user.application.errors.UserAppError;
import jpolanco.springbootapp.user.application.ports.output.UserRepository;
import jpolanco.springbootapp.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;


@RequiredArgsConstructor
@Component
public class UserValidation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean isInvalidUUID(String userId) {
        try {
            UUID.fromString(userId);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
    public boolean isUserExists(String userId) {
        return userRepository.findById(userId).isPresent();
    }
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Result<User> onUpdatePasswordIsValid(String newPassword, String oldPassword, String userId) {
        if (isInvalidUUID(userId)) {
            return Result.failure(UserAppError.INVALID_ID_FORMAT);
        }
        if (newPassword.equals(oldPassword)) {
            return Result.failure(UserAppError.PASSWORDS_ARE_SAME);
        }
        var OptionalUser = userRepository.findById(userId);
        if (OptionalUser.isEmpty()) {
            return Result.failure(UserAppError.USER_NOT_FOUND);
        }
        var user = OptionalUser.get();
        if (!user.isActive()) {
            return Result.failure(UserAppError.USER_NOT_ACTIVE);
        }
        if (!passwordEncoder.matches(oldPassword, user.getEncodedPassword())) {
            return Result.failure(UserAppError.OLD_PASSWORD_IS_INCORRECT);
        }
        return Result.success(user);
    }

    public Result<User> onUpdateEmailIsValid(String newEmail, String userId) {
        var OptionalUser = userRepository.findById(userId);
        if (OptionalUser.isEmpty()) {
            return Result.failure(UserAppError.USER_NOT_FOUND);
        }
        var user = OptionalUser.get();
        if (isEmailExists(newEmail) && !user.getEmail().equals(newEmail)) {
            return Result.failure(UserAppError.EMAIL_IS_ALREADY_IN_USE);
        }
        return Result.success();
    }

    public Result<User> basicValid(String userId) {
        if (isInvalidUUID(userId)) {
            return Result.failure(UserAppError.INVALID_ID_FORMAT);
        }
        var maybeUser = userRepository.findById(userId);
        if (maybeUser.isEmpty()) {
            return Result.failure(UserAppError.USER_NOT_FOUND);
        }
        var user = maybeUser.get();
        return Result.success(user);
    }
}
