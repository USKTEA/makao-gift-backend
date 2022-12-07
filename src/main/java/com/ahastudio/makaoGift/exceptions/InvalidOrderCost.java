package com.ahastudio.makaoGift.exceptions;

public class InvalidOrderCost extends OrderCreateFailed {
    public InvalidOrderCost() {
        super("비정상적인 주문 금액입니다");
    }
}
