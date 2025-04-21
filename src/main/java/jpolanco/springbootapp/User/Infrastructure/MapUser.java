package jpolanco.springbootapp.User.Infrastructure;

import jpolanco.springbootapp.Shared.Domain.patterns.MappingStrategy;
import jpolanco.springbootapp.Shared.Domain.patterns.Result;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Email;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Name;
import jpolanco.springbootapp.Shared.Domain.valueobjects.QR;
import jpolanco.springbootapp.User.Domain.Role;
import jpolanco.springbootapp.User.Domain.User;
import jpolanco.springbootapp.User.Infrastructure.repository.RolesEntity;
import jpolanco.springbootapp.User.Infrastructure.repository.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class MapUser implements MappingStrategy<UserEntity, User> {
    @Override
    public User mapToDomain(UserEntity infrastructure) {

        Result<Email> emailResult = Email.create(infrastructure.getEmail());
        Result<Name> nameResult = Name.create(infrastructure.getFirstName(), infrastructure.getLastName());
        Result<QR> qrResult = QR.create(infrastructure.getQrPath());

        Email email = emailResult.getValue();
        Name name = nameResult.getValue();
        QR qr = qrResult.getValue();

        User user = new User();
        HashSet<Role> roles = new HashSet<>();
        for (RolesEntity role : infrastructure.getRoles()) {
            roles.add(new Role(role.getId(), role.getName()));
        }
        user.setId(infrastructure.getId());
        user.setEmail(email);
        user.setName(name);
        user.setPassword(infrastructure.getPassword());
        user.setQr(qr);
        user.setRoles(roles);
        user.setStatus(infrastructure.getStatus());
        user.setCreatedAt(infrastructure.getCreatedAt());
        return user;
    }

    @Override
    public UserEntity mapToInfrastructure(User domain) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(domain.getEmail().getValue());
        userEntity.setFirstName(domain.getName().getFirstName());
        userEntity.setLastName(domain.getName().getLastName());
        userEntity.setPassword(domain.getPassword());
        userEntity.setQrPath(domain.getQr().getPath());
        return userEntity;
    }
}
