package com.ahastudio.makaoGift.exceptions;

public class InvalidOrderQuantity extends OrderCreateFailed {
    public InvalidOrderQuantity() {
        super("주문 수량이 잘못 되었습니다");
    }
}
