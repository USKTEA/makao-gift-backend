package com.ahastudio.makaoGift.exceptions;

public class OrderCreateFailed extends RuntimeException {
    public OrderCreateFailed(String errorMessage) {
        super(errorMessage);
    }
}
