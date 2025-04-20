package jpolanco.springbootapp.Shared.Domain;

public class Validator<T> {
    private ValidationStrategy<T> strategy;

    public void setStrategy(ValidationStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void validate(T value) {
        strategy.validate(value);
    }
}
