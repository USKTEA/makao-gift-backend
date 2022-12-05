package com.ahastudio.makaoGift.exceptions;

public class OrderRequestFailed extends RuntimeException{
    public OrderRequestFailed(String errorMessage) {
        super(errorMessage);
    }
}
