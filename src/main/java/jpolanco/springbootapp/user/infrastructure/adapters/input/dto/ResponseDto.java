package jpolanco.springbootapp.user.infrastructure.adapters.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ResponseDto {
    private String message;
    private Object data;
    private boolean ok;

    private ResponseDto(String message, Object data, boolean ok) {
        this.message = message;
        this.data = data;
        this.ok = ok;
    }

    public static ResponseDto ok(String message, Object data) {
        return new ResponseDto(message, data, true);
    }

    public static ResponseDto error(String message, Object data) {
        return new ResponseDto(message, data, false);
    }

    public boolean isOk() {
        return ok;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    protected static class Response {
        private String message;
        private Object data;
    }

    public Response toResponse() {
        return new Response(message, data);
    }
}
