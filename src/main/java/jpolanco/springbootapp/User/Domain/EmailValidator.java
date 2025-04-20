package jpolanco.springbootapp.User.Domain;

import jpolanco.springbootapp.Shared.Domain.ValidationStrategy;

public class EmailValidator implements ValidationStrategy<String> {

    @Override
    public boolean isValid(String value) {
        return value != null && value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    @Override
    public String getErrorMessage() {
        return "Email is not valid";
    }
}
