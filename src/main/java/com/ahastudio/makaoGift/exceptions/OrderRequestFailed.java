package com.ahastudio.makaoGift.exceptions;

public class OrderRequestFailed extends RuntimeException {
    public OrderRequestFailed() {
        super("잘못된 요청입니다");
    }
}
