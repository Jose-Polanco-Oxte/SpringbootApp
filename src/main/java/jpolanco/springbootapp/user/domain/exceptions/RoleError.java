package jpolanco.springbootapp.user.domain.exceptions;

import jpolanco.springbootapp.shared.application.Error;

public class RoleError extends Error {

    public RoleError(String code, String message) {
        super(code, message);
    }

    public static RoleError ContainDots = new RoleError("RoleContainDots", "Role cannot contain dots");
    public static RoleError TooLong = new RoleError("RoleTooLong", "Role cannot be longer than 20 characters");
    public static RoleError TooShort = new RoleError("RoleTooShort", "Role cannot be shorter than 3 characters");
    public static RoleError InvalidFormat = new RoleError("RoleInvalidFormat", "Role can only contain letters");
}
