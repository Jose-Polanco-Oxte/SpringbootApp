package jpolanco.springbootapp.user.application.ports.input;

public interface QRService {
    void generate(String fileName, String content);
    boolean exist(String fileName);
    void delete(String fileName);
}
