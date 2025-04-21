package jpolanco.springbootapp.Shared.Domain.patterns;

public interface ValidationStrategy<T> {
    Result<T> validate();
}