package jpolanco.springbootapp.User.Infrastructure.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.User.Domain.exceptions.UserNotValid;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).{8,30}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password cannot be null or empty")
                    .addConstraintViolation();
            return false;
        }
        if (password.contains(" ")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password cannot contain spaces")
                    .addConstraintViolation();
            return false;
        }
        if (password.contains("..")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password cannot contain consecutive dots")
                    .addConstraintViolation();
            return false;
        }
        //Unicode characters
        if (password.chars().anyMatch(c -> c > 127)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password cannot contain unicode characters")
                    .addConstraintViolation();
            return false;
        }
        if (!password.matches(PASSWORD_REGEX)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password must contain at least one letter, one number, and one special character")
                    .addConstraintViolation();
            return false;
        }
        if (password.equalsIgnoreCase("password")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password cannot be 'password'")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
