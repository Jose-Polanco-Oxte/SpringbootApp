package jpolanco.springbootapp.user.infrastructure.adapters.input.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.UserUpdateDto;
import jpolanco.springbootapp.user.infrastructure.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    @PostMapping("/update/{userId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserUpdateDto userCreationDto,
                                                  @PathVariable String userId) {
        var command = userServices.updateUser(userCreationDto, userId);
        if (!command.isOk()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(command.toResponse());
        }
        return ResponseEntity.ok(command.toResponse());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable String userId) {
        var command = userServices.getUser(userId);
        if (!command.isOk()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(command.toResponse());
        }
        return ResponseEntity.ok(command.toResponse());
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        var command = userServices.getUserByEmail(email);
        if (!command.isOk()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(command.toResponse());
        }
        return ResponseEntity.ok(command.toResponse());
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
        var command = userServices.deleteUser(userId);
        if (!command.isOk()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(command.toResponse());
        }
        return ResponseEntity.ok(command.toResponse());
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        System.out.println("getAllUsers");
        var command = userServices.getAllUsers();
        if (!command.isOk()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(command.toResponse());
        }
        return ResponseEntity.ok(command.toResponse());
    }
}
