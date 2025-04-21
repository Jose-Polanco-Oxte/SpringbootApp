package jpolanco.springbootapp.User.Domain.repositories;

import jpolanco.springbootapp.User.Domain.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
