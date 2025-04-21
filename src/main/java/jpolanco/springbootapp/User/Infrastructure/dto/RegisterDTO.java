package jpolanco.springbootapp.User.Infrastructure.dto;


import jakarta.validation.constraints.*;
import jpolanco.springbootapp.User.Infrastructure.validators.ValidPassword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @ValidPassword
    private String password;

    public RegisterDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
