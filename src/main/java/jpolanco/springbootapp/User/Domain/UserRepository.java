package jpolanco.springbootapp.User.Domain;

import jpolanco.springbootapp.Shared.Domain.repository.RepositoryCRUD;

public interface UserRepository extends RepositoryCRUD<User, Long> {
    boolean existByEmail(String email);
}
