package com.ahastudio.makaoGift.exceptions;

public class OrderItemError extends OrderCreateFailed {
    public OrderItemError() {
        super("상품 정보가 정확하지 않습니다");
    }
}
