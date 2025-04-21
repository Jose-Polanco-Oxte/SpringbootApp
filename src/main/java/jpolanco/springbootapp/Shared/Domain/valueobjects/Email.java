package jpolanco.springbootapp.Shared.Domain.valueobjects;

import jpolanco.springbootapp.Shared.Domain.patterns.Result;

public class Email {
    private final String value;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$";

    private Email(String value) {
        this.value = value;
    }

    public static Result<Email> create (String value) {
        if ( value == null || value.isBlank()) {
            return Result.failure("Email value is empty", "Email cannot be empty");
        }
        if (value.length() > 254) {
            return Result.failure("Email value is too long", "Email cannot be longer than 254 characters");
        }
        if (value.length() < 5) {
            return Result.failure("Email value is too short", "Email cannot be shorter than 5 characters");
        }
        if (value.contains(" ")) {
            return Result.failure("Email contain spaces", "Email cannot contain spaces");
        }
        if (value.contains("..")) {
            return Result.failure("Email contain consecutive dots", "Email cannot contain consecutive dots");
        }
        if (value.startsWith(".") || value.endsWith(".")) {
            return Result.failure("Email contain periods", "Email cannot contain periods");
        }
        if (!value.contains("@")) {
            return Result.failure("Email does not contain at sign", "Email must contain at sign");
        }
        if (value.startsWith("@") || value.endsWith("@")) {
            return Result.failure("Email contain at sign", "Email cannot start or end with at sign");
        }
        if (!value.matches(EMAIL_REGEX)) {
            return Result.failure("Email is not valid", "Email must be valid");
        }
        return Result.success(new Email(value));
    }

    public String getDomain() {
        return value.substring(value.indexOf("@") + 1, value.indexOf("."));
    }

    public String getTLD() {
        return value.substring(value.indexOf(".") + 1);
    }

    public String getUsername() {
        return value.substring(0, value.indexOf("@"));
    }

    public String getValue() {
        return value;
    }
}
