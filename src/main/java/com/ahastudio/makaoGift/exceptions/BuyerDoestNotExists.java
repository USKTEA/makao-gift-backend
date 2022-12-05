package com.ahastudio.makaoGift.exceptions;

public class BuyerDoestNotExists extends OrderRequestFailed{
    public BuyerDoestNotExists() {
        super("구매자 정보가 존재하지 않습니다");
    }
}
