package jpolanco.springbootapp.User.Infrastructure.controller;

import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.Shared.Domain.patterns.Result;
import jpolanco.springbootapp.Shared.Infrastructure.SimpleResponse;
import jpolanco.springbootapp.User.Application.exceptions.RegistrationFailure;
import jpolanco.springbootapp.User.Infrastructure.dto.RegisterDTO;
import jpolanco.springbootapp.User.Infrastructure.services.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @Transactional
    @PutMapping("/register")
    public ResponseEntity<SimpleResponse> register(@RequestBody RegisterDTO registerDTO) {
        Result<?> result = createUserService.createUser(registerDTO);
        if (!result.isSuccess()) {
            throw new RegistrationFailure(result.getError(), new ExceptionDetails(result.getUserFriendlyMessage(), "low"));
        }
        return ResponseEntity.ok(new SimpleResponse("User registered successfully"));
    }
}