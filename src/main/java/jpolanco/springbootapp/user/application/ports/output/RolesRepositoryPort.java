package jpolanco.springbootapp.user.application.ports.output;

public interface RolesRepositoryPort {
    boolean existsByName(String name);
    void save(String name);
    String findByName(String name);
}
