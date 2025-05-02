package jpolanco.springbootapp.user.infrastructure.adapters.input.controllers;

import jakarta.validation.Valid;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.LoginRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.RegisterRequest;
import jpolanco.springbootapp.user.infrastructure.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest request) {
        var response = service.register(request);
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok().body(response.getValue());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request) {
        var response = service.login(request);
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok().body(response.getValue());
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        var response = service.refresh(authorizationHeader);
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok().body(response.getValue());
    }
}
