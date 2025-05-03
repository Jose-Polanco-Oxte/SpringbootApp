package jpolanco.springbootapp.user.infrastructure.events;

import jpolanco.springbootapp.user.application.events.UserRegistered;
import jpolanco.springbootapp.user.infrastructure.services.interfaces.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRegisteredListener {
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(UserRegisteredListener.class);

    @EventListener
    public void handleUserRegistered(UserRegistered event) {
        logger.info("User registered: userId= {}, email= {}, registerDate= {}", event.getUserId(), event.getEmail(), event.getTimeStamp());
        emailService.sendVerificationEmail(event.getEmail());
    }
}
