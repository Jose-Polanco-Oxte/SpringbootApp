package jpolanco.springbootapp.User.Domain;

import jpolanco.springbootapp.Shared.Domain.ValidationStrategy;

public class NameValidator implements ValidationStrategy<String> {

    @Override
    public boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return "Name is not valid";
    }
}
