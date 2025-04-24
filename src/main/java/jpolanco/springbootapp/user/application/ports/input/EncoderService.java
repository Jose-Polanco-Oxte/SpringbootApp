package jpolanco.springbootapp.user.application.ports.input;

public interface EncoderService {
    String encode(String data);
    boolean matches(String rawData, String encodedData);
}
