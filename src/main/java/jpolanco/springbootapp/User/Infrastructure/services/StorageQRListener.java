package jpolanco.springbootapp.User.Infrastructure.services;

import jpolanco.springbootapp.Shared.Domain.exceptions.ExceptionDetails;
import jpolanco.springbootapp.Shared.Infrastructure.ZxingQRService;
import jpolanco.springbootapp.User.Infrastructure.exception.DataFailure;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StorageQRListener {

    private final ZxingQRService QRService;

    public StorageQRListener(ZxingQRService QRService) {
        this.QRService = QRService;
    }

    /**
     * This method listens for the UserCreatedEvent and generates a QR code for the user.
     *
     * @param event The UserCreatedEvent containing user information.
     */

    @EventListener
    public void handle(UserCreatedEvent event) {
        try {
            System.out.println("Generating QR code for user: " + event.getEmail());
            if (QRService.existsQRcode(event.getEmail().split("@")[0])) {
                throw new DataFailure("QR is already stored",
                        new ExceptionDetails("User already exist", "low"));
            } else {
                QRService.generateQRcode(event.getEmail().split("@")[0]);
            }
        } catch (DataFailure e) {
            // Handle the exception as needed, e.g., log it or rethrow it
            System.err.println("Error generating QR code: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
