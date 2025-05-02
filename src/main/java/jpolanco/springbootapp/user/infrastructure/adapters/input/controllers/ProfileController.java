package jpolanco.springbootapp.user.infrastructure.adapters.input.controllers;
import jakarta.validation.Valid;
import jpolanco.springbootapp.config.auth.MyUserDetails;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.UpdateEmailRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.UpdateNameRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.UpdatePasswordRequest;
import jpolanco.springbootapp.user.infrastructure.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService service;

    @GetMapping("/me")
    public ResponseEntity<Object> getMe(@AuthenticationPrincipal MyUserDetails userDetails) {
        var response = service.get(userDetails.getId());
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }

    @PostMapping("/change-name")
    public ResponseEntity<Object> changeMyName(@AuthenticationPrincipal MyUserDetails userDetails, @Valid @RequestBody UpdateNameRequest request) {
        var response = service.changeName(userDetails.getId(), request);
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }

    @PostMapping("/change-email")
    public ResponseEntity<Object> changeMyEmail(@AuthenticationPrincipal MyUserDetails userDetails, @Valid @RequestBody UpdateEmailRequest request) {
        var response = service.changeEmail(userDetails.getId(), request);
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }
    @PostMapping("/change-password")
    public ResponseEntity<Object> updatePassword(@AuthenticationPrincipal MyUserDetails userDetails, @Valid @RequestBody UpdatePasswordRequest request) {
        var response = service.changePassword(userDetails.getId(), request);
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<Object> deleteAccount(@AuthenticationPrincipal MyUserDetails userDetails) {
        var response = service.deleteMe(userDetails.getId());
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        return ResponseEntity.ok(response.getValue());
    }
}
