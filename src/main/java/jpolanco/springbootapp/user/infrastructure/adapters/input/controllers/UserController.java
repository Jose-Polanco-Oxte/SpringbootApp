package jpolanco.springbootapp.user.infrastructure.adapters.input.controllers;

import jakarta.validation.Valid;
import jpolanco.springbootapp.user.application.ports.input.UserServicePort;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.ResponseDto;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.UserCreationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServicePort userServicePort;

    public UserController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @PutMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UserCreationDto userCreationDto) {
        var maybeUser = userServicePort.create(
                new UserServicePort.RegisterCommand(
                        userCreationDto.getFirstName(),
                        userCreationDto.getLastName(),
                        userCreationDto.getEmail(),
                        userCreationDto.getPassword()
                )
        );
        ResponseDto responseDto = new ResponseDto();
        if (maybeUser.isFailure()) {
            responseDto.setMessage(maybeUser.getError().toString());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } else {
            responseDto.setMessage("User created successfully");
            responseDto.setData(maybeUser.getValue());
        }
        return ResponseEntity.ok(responseDto);
    }
}
