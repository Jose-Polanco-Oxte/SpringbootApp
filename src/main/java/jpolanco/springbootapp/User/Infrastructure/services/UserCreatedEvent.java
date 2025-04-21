package jpolanco.springbootapp.User.Infrastructure.services;

import lombok.Getter;

@Getter
public class UserCreatedEvent {
    private final Long userId;
    private final String email;

    public UserCreatedEvent(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public UserCreatedEvent(String email) {
        this.userId = null;
        this.email = email;
    }
}
