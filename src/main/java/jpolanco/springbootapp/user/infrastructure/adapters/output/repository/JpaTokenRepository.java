package jpolanco.springbootapp.user.infrastructure.adapters.output.repository;


import jpolanco.springbootapp.user.infrastructure.adapters.output.persistence.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaTokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);
    void deleteAllByUserId(UUID user_id);

    List<TokenEntity> findAllByUserId(UUID user_id);
}
