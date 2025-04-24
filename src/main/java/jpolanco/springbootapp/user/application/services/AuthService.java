package jpolanco.springbootapp.user.application.services;

import jpolanco.springbootapp.user.domain.model.Roles;

import java.util.Set;

public interface AuthService {
    String getId();
    Set<String> getRoles();
    String getUsername();
    String getEmail();
}
