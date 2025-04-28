package jpolanco.springbootapp.user.application.ports.output;

import java.time.Instant;
import java.util.List;

public record ReturnUserCommand(String id,
                                String firstName,
                                String lastName,
                                String email,
                                String qrFileName,
                                String status,
                                List<String> roles,
                                Instant createdAt) {
}
