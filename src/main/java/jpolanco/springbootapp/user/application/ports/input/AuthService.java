package jpolanco.springbootapp.user.application.ports.input;

import java.util.Set;

public interface AuthService {
    String getId();
    Set<String> getRoles();
    String getUsername();
    String getEmail();
}
