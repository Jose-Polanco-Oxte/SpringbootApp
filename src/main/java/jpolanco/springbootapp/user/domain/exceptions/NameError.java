package jpolanco.springbootapp.user.domain.exceptions;

import jpolanco.springbootapp.shared.application.Error;

public class NameError extends Error {
    public NameError(String code, String message) {
        super(code, message);
    }

    public static NameError TooShort = new NameError("NameTooShort", "Name must be at least 2 characters long");
    public static NameError TooLong = new NameError("NameTooLong", "Name must be at most 50 characters long");
    public static NameError InvalidCharacters = new NameError("InvalidCharacters", "Name must contain only letters");
}
