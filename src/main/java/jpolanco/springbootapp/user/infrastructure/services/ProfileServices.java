package jpolanco.springbootapp.user.infrastructure.services;

import jpolanco.springbootapp.user.application.ports.input.TokenService;
import jpolanco.springbootapp.user.application.services.uc.GetUserByIdUC;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileServices {
    private final GetUserByIdUC getUserByIdUC;

    public ResponseDto getMe(String userId) {
        var result = getUserByIdUC.execute(userId);
        if (result.isFailure()) {
            return ResponseDto.error(result.getMessage(), null);
        }
        var user = result.getValue();
        if (user.status().equals("INACTIVE")) {
            return ResponseDto.error("Your account is inactive, check faq", null);
        }
        return ResponseDto.ok("User retrieved successfully", result.getValue());
    }
}
