package jpolanco.springbootapp.user.infrastructure.services;

import jpolanco.springbootapp.user.application.ports.input.EncoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Encoder implements EncoderService {

    private final PasswordEncoder encoder;

    @Override
    public String encode(String data) {
        return encoder.encode(data);
    }

    @Override
    public boolean matches(String rawData, String encodedData) {
        return encoder.matches(rawData, encodedData);
    }
}
