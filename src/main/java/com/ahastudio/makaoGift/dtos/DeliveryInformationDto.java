package com.ahastudio.makaoGift.dtos;

public class DeliveryInformationDto {
    private String recipient;
    private String address;
    private String message;

    public DeliveryInformationDto() {
    }

    public DeliveryInformationDto(String recipient, String address, String message) {
        this.recipient = recipient;
        this.address = address;
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }
}
