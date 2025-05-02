package jpolanco.springbootapp.user.application.ports.input;

public interface EncoderProvider {
    String encode(String data);
    boolean matches(String rawData, String encodedData);
}
