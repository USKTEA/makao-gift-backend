package com.ahastudio.makaoGift.exceptions;

public class AmountNotEnough extends OrderCreateFailed {
    public AmountNotEnough() {
        super("잔액이 부족합니다");
    }
}
