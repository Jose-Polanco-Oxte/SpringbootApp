package jpolanco.springbootapp.user.infrastructure.services.interfaces;

import jpolanco.springbootapp.shared.application.Dto;
import jpolanco.springbootapp.shared.domain.Result;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.LoginRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.RegisterRequest;

public interface AuthService {
    Result<Dto> login(LoginRequest request);
    Result<Dto> register(RegisterRequest request);
    Result<Dto> refresh(String authHeader);
}
