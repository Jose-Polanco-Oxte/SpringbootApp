package jpolanco.springbootapp.user.infrastructure.adapters.output.repository;


import jpolanco.springbootapp.user.infrastructure.adapters.output.persistence.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaTokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    void deleteByToken(String token);
    List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(UUID user_id);
}
