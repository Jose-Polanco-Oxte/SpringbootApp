package jpolanco.springbootapp.user.infrastructure.adapters.input.controllers;

import jakarta.validation.Valid;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.AllUserUpdateRequest;
import jpolanco.springbootapp.user.infrastructure.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @PostMapping("/update/{userId}")
    public ResponseEntity<Object> update(@Valid @RequestBody AllUserUpdateRequest request,
                                          @PathVariable String userId) {
        var response = service.updateUser(request, userId);
        if (response.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable String userId) {
        var response = service.getUserById(userId);
        if (response.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        var response = service.getUserByEmail(email);
        if (response.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<Object> delete(@PathVariable String userId) {
        var response = service.deleteUser(userId);
        if (response.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        var response = service.getAll();
        if (response.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }
}
