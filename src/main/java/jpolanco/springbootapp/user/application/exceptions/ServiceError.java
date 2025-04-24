package jpolanco.springbootapp.user.application.exceptions;

import jpolanco.springbootapp.shared.application.Error;

public class ServiceError extends Error {

    public ServiceError(String code, String message) {
        super(code, message);
    }

    public final static ServiceError USER_NOT_FOUND = new ServiceError("USER_NOT_FOUND", "User not found");
    public final static ServiceError USER_ALREADY_EXISTS = new ServiceError("USER_ALREADY_EXISTS", "User already exists");
    public final static ServiceError USER_NOT_ACTIVE = new ServiceError("USER_NOT_ACTIVE", "User not active");
    public final static ServiceError USER_NOT_AUTHORIZED = new ServiceError("USER_NOT_AUTHORIZED", "User not authorized");
    public final static ServiceError QR_CODE_EXISTS = new ServiceError("QR_CODE_EXISTS", "QR code already exists");
}