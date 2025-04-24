package jpolanco.springbootapp.shared.domain;

import java.util.Optional;

public interface CRUDRepository <T, ID>{
    void save(T entity);
    Optional<T> findById(ID id);
    void deleteById(ID id);
    void update(T entity);
}
