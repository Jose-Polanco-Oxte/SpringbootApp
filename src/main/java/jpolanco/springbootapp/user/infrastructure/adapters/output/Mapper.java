package jpolanco.springbootapp.user.infrastructure.adapters.output;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.user.domain.model.User;

import jpolanco.springbootapp.user.domain.model.valueobjects.Role;
import jpolanco.springbootapp.user.infrastructure.adapters.output.persistence.RoleEntity;
import jpolanco.springbootapp.user.infrastructure.adapters.output.persistence.UserEntity;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.stream.Collectors;

public class Mapper {
    public static User toDomain(UserEntity userEntity) {

        Result<User> result = User.load(
                userEntity.getId().toString(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRoles()
                        .stream()
                        .map(RoleEntity -> Role.create(RoleEntity.getName())
                                        .getValue())
                        .collect(Collectors.toSet()),
                userEntity.getStatus(),
                userEntity.getQrFileName(),
                userEntity.getCreatedAt()
        );
        if (result.isFailure()) {
            throw new IllegalArgumentException("Data do not match : " + result.getError());
        }
        return result.getValue();
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                UUID.nameUUIDFromBytes(user.getId().getBytes(StandardCharsets.UTF_8)),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getEncodedPassword(),
                user.getRoles().stream()
                        .map(role -> new RoleEntity(role.getValue()))
                        .collect(Collectors.toSet()),
                user.getStatus(),
                user.getCreatedAt(),
                user.getQRFileName()
        );
    }
}
