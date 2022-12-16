package com.gyk.myauction.utils;

public class AuctionPriceException extends Exception{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuctionPriceException(String message) {
        this.message = message;
    }
}
