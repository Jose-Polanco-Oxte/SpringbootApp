package jpolanco.springbootapp.user.application.ports.output;

public record ReturnLoginCommand(String firstName, String lastName, String email) {
}