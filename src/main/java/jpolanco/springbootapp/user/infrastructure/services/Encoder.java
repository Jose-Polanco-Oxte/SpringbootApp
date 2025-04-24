package jpolanco.springbootapp.user.infrastructure.services;

import jpolanco.springbootapp.user.application.ports.input.EncoderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encoder implements EncoderService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    Encoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Encoder() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String data) {
        return bCryptPasswordEncoder.encode(data);
    }

    @Override
    public boolean matches(String rawData, String encodedData) {
        return bCryptPasswordEncoder.matches(rawData, encodedData);
    }
}
