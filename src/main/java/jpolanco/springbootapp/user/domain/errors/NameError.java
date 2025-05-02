package jpolanco.springbootapp.user.domain.errors;

import jpolanco.springbootapp.shared.domain.Error;

public class NameError extends Error {
    public NameError(String code, String message) {
        super(code, message);
    }

    public static NameError TOO_SHORT = new NameError("NAME_TOO_SHORT", "Name must be at least 2 characters long");
    public static NameError TOO_LONG = new NameError("MANE_TOO_LONG", "Name must be at most 50 characters long");
    public static NameError INVALID_CHARACTERS = new NameError("INVALID_CHARACTERS", "Name must contain only letters");
}
