package jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request;

import jakarta.validation.constraints.Email;
import jpolanco.springbootapp.shared.application.Dto;

import java.util.List;


public record AllUserUpdateRequest(
        String firstName,
        String lastName,
        @Email(message = "Email should be valid")
        String email,
        String password,
        String status,
        List<String> roles
) implements Dto {
}
