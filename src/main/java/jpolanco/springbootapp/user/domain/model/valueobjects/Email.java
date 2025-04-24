package jpolanco.springbootapp.user.domain.model.valueobjects;

import jpolanco.springbootapp.shared.application.Error;
import jpolanco.springbootapp.shared.application.Result;

public class Email {
    private String value;
    private static String EMAIL_REGEX = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}";

    private Email(String email) {
        this.value = email;
    }

    public static Result<Email> create(String value) {
        if (value == null || value.isBlank()) {
            return Result.failure(Error.NullValue);
        }
        if (!value.matches(EMAIL_REGEX)) {
            return Result.failure(new Error("InvalidEmail", "Invalid email format"));
        }
        return Result.success(new Email(value));
    }

    public String getValue() {
        return value;
    }
}
