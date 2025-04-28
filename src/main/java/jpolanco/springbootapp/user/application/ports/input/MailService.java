package jpolanco.springbootapp.user.application.ports.input;

public interface MailService {
    void send(String to, String subject, String body);
}
