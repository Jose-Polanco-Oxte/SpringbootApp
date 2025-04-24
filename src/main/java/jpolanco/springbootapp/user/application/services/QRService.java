package jpolanco.springbootapp.user.application.services;

public interface QRService {
    void generate(String fileName, String content);
    boolean exist(String fileName);
}
