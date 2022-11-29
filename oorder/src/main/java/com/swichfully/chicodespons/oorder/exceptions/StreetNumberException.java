package com.swichfully.chicodespons.oorder.exceptions;

public class StreetNumberException extends IllegalArgumentException {
    public StreetNumberException() {
        super("Incorrect streetNumber");
    }

    public StreetNumberException(String s) {
        super(s);
    }
}
