package jpolanco.springbootapp.user.domain.model.valueobjects;

import jpolanco.springbootapp.shared.application.Error;
import jpolanco.springbootapp.shared.application.Result;

public class QRFileName {
    private String fileName;

    private QRFileName(String value) {
        this.fileName = value;
    }

    public static Result<QRFileName> create(String value) {
        if (value == null || value.isEmpty()) {
            return Result.failure(Error.NULLVALUE);
        }
        return Result.success(new QRFileName(value));
    }

    public String getValue() {
        return fileName;
    }

}
