package jpolanco.springbootapp.user.infrastructure.exceptions;

public class DataFailure extends RuntimeException {
    public DataFailure(String message) {
        super(message);
    }
}
