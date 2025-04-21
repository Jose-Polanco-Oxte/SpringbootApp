package jpolanco.springbootapp.User.Domain;

import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Email;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Name;
import jpolanco.springbootapp.Shared.Domain.valueobjects.QR;
import jpolanco.springbootapp.User.Domain.exceptions.UserNotValid;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private Email email;
    private Name name;
    private String password;
    private QR qr;
    private Instant createdAt = Instant.now();
    private Set<Role> roles = new HashSet<>();
    private String status = "ACTIVE";

    public User(Long id, Email email, Name name, String password, Instant createdAt, QR qr, Set<Role> roles,  String status) {
        if (id == null || id <= 0) {
            throw new UserNotValid("invalid id",
                    new ExceptionDetails("ID cannot be null or negative", "low"));
        }
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.qr = qr;
        this.roles = roles;
        this.status = status;
    }

    public User () {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public QR getQr() {
        return qr;
    }

    public void setQr(QR qr) {
        this.qr = qr;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}