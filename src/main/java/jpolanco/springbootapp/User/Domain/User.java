package jpolanco.springbootapp.User.Domain;

import jpolanco.springbootapp.Shared.Domain.Validator;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Email;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Name;
import jpolanco.springbootapp.Shared.Domain.valueobjects.QR;

public class User {
    private Long id;
    private Email email;
    private Name name;
    private String password;
    private QR qr;
    private Validator<String> validator = new Validator<>();

    public User(Long id, Email email, Name name, String password, QR qr) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        if (qr == null || qr.getPath().isBlank()) {
            throw new IllegalArgumentException("QR path cannot be null or empty");
        }

        validator.setStrategy(new EmailValidator());
        validator.validate(email.getEmail());
        validator.setStrategy(new NameValidator());
        validator.validate(name.getFirstName());
        validator.validate(name.getLastName());
        validator.setStrategy(new PasswordValidator());
        validator.validate(password);

        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.qr = qr;
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

    public Validator<String> getValidator() {
        return validator;
    }

    public void setValidator(Validator<String> validator) {
        this.validator = validator;
    }

    public QR getQr() {
        return qr;
    }

    public void setQr(QR qr) {
        this.qr = qr;
    }
}