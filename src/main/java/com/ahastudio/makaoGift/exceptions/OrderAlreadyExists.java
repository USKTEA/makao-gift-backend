package com.ahastudio.makaoGift.exceptions;

public class OrderAlreadyExists extends OrderCreateFailed {
    public OrderAlreadyExists() {
        super("중복된 주문입니다");
    }
}
