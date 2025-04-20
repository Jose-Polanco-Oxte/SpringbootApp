package jpolanco.springbootapp.User.Domain;

import jpolanco.springbootapp.Shared.Domain.ValidationStrategy;

public class PasswordValidator implements ValidationStrategy<String> {
    @Override
    public boolean isValid(String value) {
        return value != null && value.length() >= 8 && value.matches(".*[A-Z].*") && value.matches(".*[0-9].*");
    }

    @Override
    public String getErrorMessage() {
        return "Password must be at least 8 characters long, contain at least one uppercase letter and one number";
    }
}
