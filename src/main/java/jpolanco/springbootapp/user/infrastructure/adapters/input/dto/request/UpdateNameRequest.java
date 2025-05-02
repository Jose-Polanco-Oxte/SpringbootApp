package jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request;

import jakarta.validation.constraints.Email;

public record UpdateNameRequest(
        String firstName,
        String lastName,
        @Email(message = "Email should be valid")
        String email
){}