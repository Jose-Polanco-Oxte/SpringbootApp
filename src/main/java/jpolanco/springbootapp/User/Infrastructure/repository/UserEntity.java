package jpolanco.springbootapp.User.Infrastructure.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "qr_path", columnDefinition = "TEXT", nullable = false)
    private String qrPath;

    public UserEntity(Long id, String username, String email, String password, String qrPath) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.qrPath = qrPath;
    }

    public UserEntity() {
    }
}