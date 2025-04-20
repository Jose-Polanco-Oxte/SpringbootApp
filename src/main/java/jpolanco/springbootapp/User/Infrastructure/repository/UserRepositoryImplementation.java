package jpolanco.springbootapp.User.Infrastructure.repository;

import jpolanco.springbootapp.User.Domain.User;
import jpolanco.springbootapp.User.Domain.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplementation implements UserRepository {
    private final JpaUserRepository JpaUserRepository;

    public UserRepositoryImplementation(JpaUserRepository jpaUserRepository) {
        this.JpaUserRepository = jpaUserRepository;
    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }

    @Override
    public void save(User entity) {
        UserEntity userEntity = mapToEntity(entity);
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

    private UserEntity mapToEntity(User user) {
        return new UserEntity(user.getId(), user.getName().toString(), user.getEmail().getEmail(), user.getPassword(), user.getQr().getPath());
    }
}
