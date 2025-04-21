package jpolanco.springbootapp.Shared.Domain.exceptions;

import jpolanco.springbootapp.Shared.Layer;

public class AbstractException extends RuntimeException {
    private ExceptionDetails details;
    private Layer layer;

    public AbstractException(String message, ExceptionDetails details, Throwable e) {
        super(message, e);
        this.details = details;
    }

    public AbstractException(String message, ExceptionDetails details, Layer layer) {
        super(message);
        this.details = details;
        this.layer = layer;
    }

    public AbstractException(String message, ExceptionDetails details) {
        super(message);
        this.details = details;
    }

    public ExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(ExceptionDetails details) {
        this.details = details;
    }

    public String getLayer() {
        return layer.getLayer();
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
}
