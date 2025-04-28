package jpolanco.springbootapp.user.application.ports.input;

public interface TokenService {
    String generate();

    String decode(String token);

    void delete();

    boolean expired(String token);
}
