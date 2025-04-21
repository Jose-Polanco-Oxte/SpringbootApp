package jpolanco.springbootapp.User.Domain.repositories;

import jpolanco.springbootapp.Shared.Domain.repository.RepositoryCRUD;
import jpolanco.springbootapp.User.Domain.User;

import java.util.Optional;

public interface UserRepository extends RepositoryCRUD<User, Long> {
    Optional<User> findByEmail(String email);
}
