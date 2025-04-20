package jpolanco.springbootapp.User.Infrastructure.controller;

import jpolanco.springbootapp.User.Infrastructure.dto.RegisterDTO;
import jpolanco.springbootapp.User.Infrastructure.services.RegisterUserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RegisterUserService registerUserService;

    public UserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @Transactional
    @PutMapping("/register")
    public void registerUser(@RequestBody RegisterDTO registerDTO) {
        registerUserService.registerUser(registerDTO);
    }
}
