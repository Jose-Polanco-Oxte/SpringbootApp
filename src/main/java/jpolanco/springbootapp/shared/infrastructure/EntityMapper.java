package jpolanco.springbootapp.shared.infrastructure;

public interface EntityMapper<E, D> {
    E toEntity(D domain);
    D toDomain(E entity);
}