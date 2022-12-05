package com.ahastudio.makaoGift.exceptions;

public class InvalidDeliveryInformation extends OrderRequestFailed {
    public InvalidDeliveryInformation() {
        super("배송 정보가 잘못 되었습니다");
    }
}
