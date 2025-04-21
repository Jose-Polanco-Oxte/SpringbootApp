package jpolanco.springbootapp.User.Infrastructure.exception;

import jpolanco.springbootapp.Shared.Domain.exceptions.AbstractException;
import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.Shared.Layer;

public class DataFailure extends AbstractException {

  public DataFailure(String message, ExceptionDetails details, Throwable e) {
    super(message, details, e);
  }

  public DataFailure(String message, ExceptionDetails details, Layer layer) {
    super(message, details, layer);
  }

  public DataFailure(String message, ExceptionDetails details) {
    super(message, details, Layer.INFRASTRUCTURE);
  }
}
