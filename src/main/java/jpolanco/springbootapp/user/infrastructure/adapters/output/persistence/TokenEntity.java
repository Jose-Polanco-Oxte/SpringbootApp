package jpolanco.springbootapp.user.infrastructure.adapters.output.persistence;

import jakarta.persistence.*;
import jpolanco.springbootapp.user.domain.model.TokenStatus;
import jpolanco.springbootapp.user.domain.model.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tokens")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1000)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @Enumerated(EnumType.STRING)
    private TokenStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
