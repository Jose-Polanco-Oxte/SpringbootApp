package jpolanco.springbootapp.Shared.Domain.valueobjects;

import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.Shared.Domain.patterns.Result;
import jpolanco.springbootapp.User.Domain.exceptions.UserNotValid;

public class QR {
    private final String path;

    public QR(String path) {
        this.path = path;
    }

    public static Result<QR> create (String value) {
        if (value == null || value.isEmpty()) {
            return Result.failure("QR code is empty", "QR code cannot be empty");
        }
        return Result.success(new QR(value));
    }

    public String getPath() {
        return path;
    }
}
