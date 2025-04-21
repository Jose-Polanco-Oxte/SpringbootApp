package jpolanco.springbootapp.User.Infrastructure.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public RolesEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RolesEntity() {
    }
}
