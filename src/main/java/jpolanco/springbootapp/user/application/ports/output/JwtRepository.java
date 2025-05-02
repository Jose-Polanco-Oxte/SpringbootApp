package jpolanco.springbootapp.user.application.ports.output;

import jpolanco.springbootapp.user.domain.model.Token;

import java.util.List;
import java.util.Optional;

public interface JwtRepository {
    Optional<Token> findByToken(String token);
    void deleteAllByUserId(String userId);
    List<Token> findAllByUserId(String userId);
    void save(Token token);
    void saveAll(List<Token> tokens);
}
