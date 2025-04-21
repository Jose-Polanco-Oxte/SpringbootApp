package jpolanco.springbootapp.User.Infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<RolesEntity, Long> {
    Optional<RolesEntity> findByName(String name);
}
