package jpolanco.springbootapp.User.Domain.exceptions;

import jpolanco.springbootapp.Shared.Layer;
import jpolanco.springbootapp.Shared.Domain.exceptions.AbstractException;
import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;

public class UserNotValid extends AbstractException {

    public UserNotValid(String message, ExceptionDetails details, Throwable e) {
        super(message, details, e);
    }

    public UserNotValid(String message, ExceptionDetails exceptionDetails) {
      super(message, exceptionDetails, Layer.DOMAIN);
    }
}
