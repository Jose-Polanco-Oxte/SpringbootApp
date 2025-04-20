package jpolanco.springbootapp.Shared.Domain.repository;

public interface RepositoryCRUD<T, ID> {
    void save(T entity);

    T findById(ID id);

    void update(T entity);

    void delete(ID id);
}