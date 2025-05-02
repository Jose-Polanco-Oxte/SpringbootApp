package jpolanco.springbootapp.user.domain.model.valueobjects;

import jpolanco.springbootapp.shared.domain.Error;
import jpolanco.springbootapp.shared.domain.Result;

import java.util.UUID;

public class UserId {
    private final String value;

    private UserId(String value) {
        this.value = value;
    }

    public static Result<UserId> create(String value) {
        if (value == null || value.isEmpty()) {
            return Result.failure(Error.NULL_VALUE);
        }
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            return Result.failure(new Error("InvalidUUID", "The provided UUID is invalid."));
        }
        return Result.success(new UserId(value));
    }

    public String getValue() {
        return value;
    }
}
