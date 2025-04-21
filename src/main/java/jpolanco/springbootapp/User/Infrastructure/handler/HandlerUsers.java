package jpolanco.springbootapp.User.Infrastructure.handler;

import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionResponse;
import jpolanco.springbootapp.User.Application.exceptions.RegistrationFailure;
import jpolanco.springbootapp.User.Domain.exceptions.UserNotValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class HandlerUsers {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(UserNotValid.class)
    public ResponseEntity<ExceptionResponse> handleUserNotValid(UserNotValid exception) {
        logger.error("{}\nIn: {}", exception.getMessage(), exception.getLayer(), exception);
        return new ResponseEntity<>(exception.getDetails().getResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistrationFailure.class)
    public ResponseEntity<ExceptionResponse> handleRegistrationFailure(RegistrationFailure exception) {
        logger.error("{}\nIn: {}", exception.getMessage(), exception.getLayer(), exception);
        return new ResponseEntity<>(exception.getDetails().getResponse(), HttpStatus.BAD_REQUEST);
    }
}
