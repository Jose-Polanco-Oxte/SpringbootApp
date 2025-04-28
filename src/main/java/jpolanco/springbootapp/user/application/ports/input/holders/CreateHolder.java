package jpolanco.springbootapp.user.application.ports.input.holders;

public record CreateHolder(String firstName,
                           String lastName,
                           String email,
                           String password) {
}