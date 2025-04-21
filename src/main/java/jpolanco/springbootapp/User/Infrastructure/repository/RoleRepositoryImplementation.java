package jpolanco.springbootapp.User.Infrastructure.repository;

import jpolanco.springbootapp.User.Domain.Role;
import jpolanco.springbootapp.User.Domain.repositories.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImplementation implements RoleRepository {
    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryImplementation(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }
    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository.findByName(name).map(this::mapToDomain);
    }

    private Role mapToDomain(RolesEntity rolesEntity) {
        return new Role(rolesEntity.getId(), rolesEntity.getName());
    }
}
