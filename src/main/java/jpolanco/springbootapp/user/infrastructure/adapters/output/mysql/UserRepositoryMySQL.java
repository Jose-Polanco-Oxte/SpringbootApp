package jpolanco.springbootapp.user.infrastructure.adapters.output.mysql;

import jpolanco.springbootapp.user.application.ports.output.UserRepositoryPort;
import jpolanco.springbootapp.user.domain.model.User;
import jpolanco.springbootapp.user.infrastructure.adapters.output.repository.JpaRoleRepository;
import jpolanco.springbootapp.user.infrastructure.adapters.output.repository.JpaUserRepository;
import jpolanco.springbootapp.user.infrastructure.adapters.output.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryMySQL implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryMySQL(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(Mapper::toDomain);
    }

    @Override
    public Optional<User> findById(String userId) {
        return jpaUserRepository.findById(UUID.fromString(userId))
                .map(Mapper::toDomain);
    }

    @Override
    public User save(User user) {
        jpaUserRepository.save(Mapper.toEntity(user));
        return user;
    }

    @Override
    public void deleteById(String userId) {
        jpaUserRepository.deleteById(UUID.fromString(userId));
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll()
                .stream()
                .map(Mapper::toDomain)
                .toList();
    }
}
