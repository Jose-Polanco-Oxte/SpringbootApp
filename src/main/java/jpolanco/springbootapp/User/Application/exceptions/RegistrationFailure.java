package jpolanco.springbootapp.User.Application.exceptions;

import jpolanco.springbootapp.Shared.Layer;
import jpolanco.springbootapp.Shared.Domain.exceptions.AbstractException;
import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;

public class RegistrationFailure extends AbstractException {

    public RegistrationFailure(String message, ExceptionDetails details, Throwable e) {
        super(message, details, e);
    }

    public RegistrationFailure(String message, ExceptionDetails details) {
        super(message, details, Layer.APPLICATION);
    }
}
