package jpolanco.springbootapp.user.application.errors;

public class UserDataNotFound extends RuntimeException {
    public UserDataNotFound(String message) {
        super(message);
    }
}
