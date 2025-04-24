package jpolanco.springbootapp.user.infrastructure.adapters.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object data;

    public ResponseDto(String message) {
        this.message = message;
        this.data = null;
    }
}
