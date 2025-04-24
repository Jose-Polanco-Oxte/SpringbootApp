package jpolanco.springbootapp.user.application.ports.output;

import jpolanco.springbootapp.user.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    Optional<User> findById(String userId);

    User save(User user);

    void deleteById(String userId);
}