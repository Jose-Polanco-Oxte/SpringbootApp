package jpolanco.springbootapp.Shared.Domain;

public interface ValidationStrategy<T> {
    boolean isValid(T value);

    String getErrorMessage();

    default void validate(T value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException(getErrorMessage());
        }
    }
}