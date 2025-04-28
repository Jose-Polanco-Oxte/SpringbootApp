package jpolanco.springbootapp.user.domain.errors;

import jpolanco.springbootapp.shared.application.Error;

public class NameError extends Error {
    public NameError(String code, String message) {
        super(code, message);
    }

    public static NameError TOOSHORT = new NameError("NAME_TOO_SHORT", "Name must be at least 2 characters long");
    public static NameError TOOLONG = new NameError("MANE_TOO_LONG", "Name must be at most 50 characters long");
    public static NameError INVALIDCHARACTERS = new NameError("INVALID_CHARACTERS", "Name must contain only letters");
}
