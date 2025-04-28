package jpolanco.springbootapp.user.domain.model.valueobjects;

import jpolanco.springbootapp.shared.application.Error;
import jpolanco.springbootapp.shared.application.Result;

public class EncodedPassword {
    private String value;

    private EncodedPassword(String value) {
        this.value = value;
    }

    public static Result<EncodedPassword> create(String value) {
        if (value == null || value.isBlank()) {
            Result.failure(Error.NULLVALUE);
        }
        return Result.success(new EncodedPassword(value));
    }

    public String getValue() {
        return value;
    }
}
