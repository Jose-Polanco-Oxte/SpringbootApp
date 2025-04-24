package jpolanco.springbootapp.user.domain.repository;

import jpolanco.springbootapp.shared.domain.CRUDRepository;
import jpolanco.springbootapp.user.domain.model.User;

import java.util.Optional;

public interface UserRepository extends CRUDRepository<User, String> {
    Optional<User> findByEmail(String email);
}