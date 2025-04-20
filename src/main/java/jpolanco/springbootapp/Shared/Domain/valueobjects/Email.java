package jpolanco.springbootapp.Shared.Domain.valueobjects;

public class Email {
    private final String email;

    public Email(String email) {
        this.email = email;
    }

    public String getDomain() {
        return "";
    }

    public String getTLD() {
        return "";
    }

    public String getEmail() {
        return email;
    }
}
