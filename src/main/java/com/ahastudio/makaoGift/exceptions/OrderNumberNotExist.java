package com.ahastudio.makaoGift.exceptions;

public class OrderNumberNotExist extends OrderCreateFailed {
    public OrderNumberNotExist() {
        super("주문번호가 존재하지 않습니다");
    }
}
