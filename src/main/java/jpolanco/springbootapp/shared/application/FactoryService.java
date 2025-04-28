package jpolanco.springbootapp.shared.application;

public interface FactoryService {
    default <T> Result<T> create(T command) {
        return Result.success(command);
    }

    default <T> Result<T> update(T command, T entity) {
        return Result.success(entity);
    }
}
