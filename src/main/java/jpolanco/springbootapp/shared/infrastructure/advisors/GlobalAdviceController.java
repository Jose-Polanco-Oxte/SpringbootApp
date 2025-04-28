package jpolanco.springbootapp.shared.infrastructure.advisors;

import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalAdviceController {
    // This class is used to handle global exceptions and provide a consistent response format
    // for all controllers in the application. It can be extended to include specific exception
    // handling methods as needed.

    private static final Logger logger = LoggerFactory.getLogger(GlobalAdviceController.class);

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<String> handleAuthenticationServiceException(AuthenticationServiceException e) {
        logger.error("Authentication service exception: {}", e.getMessage(), e);
        String response = e.getMessage();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        logger.error("Bad credentials: {}", e.getMessage(), e);
        String response = "Invalid email or password";
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("HTTP message not readable: {}", e.getMessage(), e);
        String response = "Invalid request body";
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        logger.error("Null pointer exception occurred: {}", e.getMessage(), e);
        String response = "Null pointer exception occurred";
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<String> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        logger.error("Invalid data access API usage: {}", e.getMessage(), e);
        String response = e.getMessage();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException e) {
        logger.error("Resource not found: {}", e.getMessage(), e);
        String response = "Resource not found";
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("Illegal argument exception: {}", e.getMessage(), e);
        String response = "Illegal argument exception occurred: " + e.getMessage();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("An unexpected error occurred: {}", e.getMessage(), e);
        String response = "An unexpected error occurred";
        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        logger.error("Runtime exception occurred: {}", e.getMessage(), e);
        String response = "Runtime exception occurred";
        return ResponseEntity.status(500).body(response);
    }
}
