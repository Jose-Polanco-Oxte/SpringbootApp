package jpolanco.springbootapp.user.application.errors;

public class IllegalUserOperation extends RuntimeException {
    public IllegalUserOperation(String message) {
        super(message);
    }
}
