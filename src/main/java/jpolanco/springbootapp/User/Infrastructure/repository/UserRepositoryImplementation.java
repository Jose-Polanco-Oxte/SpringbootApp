package jpolanco.springbootapp.User.Infrastructure.repository;

import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.User.Application.exceptions.RegistrationFailure;
import jpolanco.springbootapp.User.Domain.User;
import jpolanco.springbootapp.User.Domain.repositories.UserRepository;
import jpolanco.springbootapp.User.Infrastructure.MapUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImplementation implements UserRepository {
    private final JpaUserRepository JpaUserRepository;
    private final MapUser MapUser;

    public UserRepositoryImplementation(JpaUserRepository jpaUserRepository, MapUser mapUser) {
        this.JpaUserRepository = jpaUserRepository;
        this.MapUser = mapUser;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return JpaUserRepository.findByEmail(email).map(MapUser::mapToDomain);
    }

    @Override
    public void save(User entity) {
        UserEntity userEntity = MapUser.mapToInfrastructure(entity);
        JpaUserRepository.save(userEntity);
    }

    @Override
    public User findById(Long aLong) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
