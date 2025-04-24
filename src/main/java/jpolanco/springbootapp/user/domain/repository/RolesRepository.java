package jpolanco.springbootapp.user.domain.repository;

public interface RolesRepository {
    boolean existsByName(String name);

    void save(String name);

    String findByName(String name);
}
