package jpolanco.springbootapp.User.Infrastructure.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public RegisterDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
