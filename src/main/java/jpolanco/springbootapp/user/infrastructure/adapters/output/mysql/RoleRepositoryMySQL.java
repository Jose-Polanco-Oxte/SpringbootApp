package jpolanco.springbootapp.user.infrastructure.adapters.output.mysql;

import jpolanco.springbootapp.user.application.ports.output.RolesRepository;
import jpolanco.springbootapp.user.infrastructure.errors.DataFailure;
import jpolanco.springbootapp.user.infrastructure.adapters.output.persistence.RoleEntity;
import jpolanco.springbootapp.user.infrastructure.adapters.output.repository.JpaRoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryMySQL implements RolesRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryMySQL(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRoleRepository.existsByName(name);
    }

    @Override
    public void save(String name) {
        jpaRoleRepository.save(new RoleEntity(name));
    }

    @Override
    public String findByName(String name) {
        return jpaRoleRepository.findByName(name)
                .map(RoleEntity::getName).orElseThrow(() ->
                        new DataFailure("Role not found"));
    }
}
