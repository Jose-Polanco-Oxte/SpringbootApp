package jpolanco.springbootapp.Shared.Domain.patterns;

public class Result<T> {
    private final T value;
    private final String error;
    private final String userFriendlyMessage;

    private Result(T value, String error, String userFriendlyMessage) {
        this.value = value;
        this.error = error;
        this.userFriendlyMessage = userFriendlyMessage;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null, null);
    }

    public static <T> Result<T> failure(String error, String message) {
        return new Result<>(null, error, message);
    }

    public boolean isSuccess() {
        return error == null;
    }

    public T getValue() {
        if (!isSuccess()) throw new IllegalStateException("Cannot get value from failure");
        return value;
    }

    public String getError() {
        return error;
    }

    public String getUserFriendlyMessage() {
        return userFriendlyMessage;
    }
}
