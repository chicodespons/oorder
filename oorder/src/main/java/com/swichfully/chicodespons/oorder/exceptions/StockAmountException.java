package com.swichfully.chicodespons.oorder.exceptions;

public class StockAmountException extends IllegalArgumentException {

    public StockAmountException() {
        super("StockAmount is not correct");
    }

    public StockAmountException(String s) {
        super(s);
    }
}
