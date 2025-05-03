package jpolanco.springbootapp.user.infrastructure.adapters.Mappers.dto;

import jpolanco.springbootapp.shared.application.Dto;
import jpolanco.springbootapp.shared.application.DtoCreator;
import jpolanco.springbootapp.shared.infrastructure.SimpleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SimpleResponseCreator {
    public Dto create(String message) {
        return new SimpleResponseDto(message);
    }
}
