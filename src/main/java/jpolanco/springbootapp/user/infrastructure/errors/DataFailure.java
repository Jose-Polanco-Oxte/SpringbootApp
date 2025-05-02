package jpolanco.springbootapp.user.infrastructure.errors;

public class DataFailure extends RuntimeException {
    public DataFailure(String message) {
        super(message);
    }
}
