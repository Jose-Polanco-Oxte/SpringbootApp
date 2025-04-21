package jpolanco.springbootapp.Shared;

public enum Layer {
    DOMAIN("Domain"),
    APPLICATION("Application"),
    INFRASTRUCTURE("Infrastructure"),
    PRESENTATION("Presentation");

    private String layer;

    Layer(String layer) {
        this.layer = layer;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }
}
