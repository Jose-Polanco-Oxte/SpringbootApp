package jpolanco.springbootapp.user.infrastructure.adapters.input.controllers;

import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.LoginRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.RegisterRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.TokenResponseDto;
import jpolanco.springbootapp.user.infrastructure.services.AuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authServices;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterRequest request) {
        var response = authServices.register(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest request) {
        var response = authServices.login(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refresh")
    public TokenResponseDto refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return authServices.refresh(authorizationHeader);
    }
}
