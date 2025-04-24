package jpolanco.springbootapp.user.domain.model.valueobjects;

import jpolanco.springbootapp.shared.application.Error;
import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.application.ResultBuilder;
import jpolanco.springbootapp.user.domain.exceptions.RoleError;

public class Role {
    private String value;

    private Role(String value) {
        this.value = value;
    }

    public static Result<Role> create(String value) {
        return ResultBuilder.from(ensureIsValidRole(value))
                .then( result -> Result.success(new Role(value)))
                .result();
    }

    private static Result<Role> ensureIsValidRole(String value) {
        if (value == null || value.isBlank()) {
            return Result.failure(Error.NullValue);
        }
        if (value.contains(".")) {
            return Result.failure(RoleError.ContainDots);
        }
        if (value.length() > 20) {
            return Result.failure(RoleError.TooLong);
        }
        if (value.length() < 3) {
            return Result.failure(RoleError.TooShort);
        }
        if (value.matches("[a-zA-Z0-9_]+")) {
            return Result.failure(RoleError.InvalidFormat);
        }
        return Result.success(new Role(value));
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        return value.equals(role.value);
    }
}
