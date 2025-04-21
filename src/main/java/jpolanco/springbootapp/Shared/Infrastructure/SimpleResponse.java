package jpolanco.springbootapp.Shared.Infrastructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse {
    private String message;

    public SimpleResponse(String message) {
        this.message = message;
    }
}
