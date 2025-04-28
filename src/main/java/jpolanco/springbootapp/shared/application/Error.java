package jpolanco.springbootapp.shared.application;

import org.apache.logging.log4j.util.Strings;

public class Error {
    final String code;
    final String message;

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error NONE = new Error(Strings.EMPTY, Strings.EMPTY);
    public static Error NULLVALUE = new Error("NULL_VALUE", "cannot be null");
}