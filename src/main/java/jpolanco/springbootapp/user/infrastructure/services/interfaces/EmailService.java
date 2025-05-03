package jpolanco.springbootapp.user.infrastructure.services.interfaces;

public interface EmailService {
    /**
     * Sends a verification email to the specified recipient.
     *
     * @param to the email address of the recipient
     */
    void sendVerificationEmail(String to);

    /**
     * Sends a password reset email to the specified recipient.
     *
     * @param recipient the email address of the recipient
     * @param subject   the subject of the email
     * @param body      the body of the email
     */
    void sendPasswordResetEmail(String recipient, String subject, String body);
}
