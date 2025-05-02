package jpolanco.springbootapp.shared.infrastructure;

import jpolanco.springbootapp.shared.application.Dto;

public record SimpleResponseDto(String message) implements Dto {
}
