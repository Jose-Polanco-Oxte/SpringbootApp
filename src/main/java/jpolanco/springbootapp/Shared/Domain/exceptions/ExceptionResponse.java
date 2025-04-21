package jpolanco.springbootapp.Shared.Domain.exceptions;

import java.time.Instant;

public class ExceptionResponse {
    private String message;
    private String severity;
    private Instant timestamp = Instant.now();

    public ExceptionResponse(String message, String severity) {
        this.message = message;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
