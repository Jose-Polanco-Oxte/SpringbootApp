package jpolanco.springbootapp.user.domain.model;

import java.time.Instant;

public abstract class DomainEvent {
    private Instant timeStamp;

    public DomainEvent() {
        this.timeStamp = Instant.now();
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }
}
