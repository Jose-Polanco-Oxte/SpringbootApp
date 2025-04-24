package jpolanco.springbootapp.user.domain.model.valueobjects;

import jpolanco.springbootapp.shared.application.Error;
import jpolanco.springbootapp.shared.application.Result;

import java.util.UUID;

public class QRFileName {
    private String fileName;

    private QRFileName(String email) {
        this.fileName = UUID.randomUUID().toString();
    }

    public static Result<QRFileName> create(String email) {
        if (email == null || email.isEmpty()) {
            return Result.failure(Error.NullValue);
        }
        return Result.success(new QRFileName(email));
    }

    public String getValue() {
        return fileName;
    }

}
