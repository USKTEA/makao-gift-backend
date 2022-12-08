package com.ahastudio.makaoGift.exceptions;

public class BuyerDoesNotExists extends OrderCreateFailed {
    public BuyerDoesNotExists() {
        super("구매자 정보가 존재하지 않습니다");
    }
}
