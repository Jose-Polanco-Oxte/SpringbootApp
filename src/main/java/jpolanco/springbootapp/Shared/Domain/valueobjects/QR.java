package jpolanco.springbootapp.Shared.Domain.valueobjects;

public class QR {
    private final String path;

    public QR(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
