package com.swichfully.chicodespons.oorder.exceptions;

public class PhoneNumberException extends IllegalArgumentException {
    public PhoneNumberException() {
        super("Incorrect phonenumber");
    }

    public PhoneNumberException(String s) {
        super(s);
    }
}
