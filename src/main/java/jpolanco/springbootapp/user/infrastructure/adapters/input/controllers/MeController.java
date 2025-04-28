package jpolanco.springbootapp.user.infrastructure.adapters.input.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class MeController {

    @GetMapping("/me/{id}")
    public ResponseEntity<Object> getMe(@PathVariable int id) {
        return null;
    }
}
