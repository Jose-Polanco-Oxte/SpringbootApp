package jpolanco.springbootapp.user.domain.errors;

import jpolanco.springbootapp.shared.application.Error;

public class RoleError extends Error {

    public RoleError(String code, String message) {
        super(code, message);
    }

    public static RoleError CONTAINDOTS = new RoleError("CONTAIN_DOTS", "Role cannot contain dots");
    public static RoleError TOOLONG = new RoleError("ROLE_TOO_LONG", "Role cannot be longer than 20 characters");
    public static RoleError TOOSHORT = new RoleError("ROLE_TOO_SHORT", "Role cannot be shorter than 3 characters");
    public static RoleError INVALIDFORMAT = new RoleError("ROLE_INVALID_FORMAT", "Role can only contain letters");
}
