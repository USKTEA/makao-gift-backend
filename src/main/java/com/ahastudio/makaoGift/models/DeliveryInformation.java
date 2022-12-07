package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.DeliveryInformationDto;
import com.ahastudio.makaoGift.exceptions.InvalidDeliveryInformation;

import javax.persistence.Embeddable;

@Embeddable
public class DeliveryInformation {
    private String recipient;
    private String address;
    private String message;

    public DeliveryInformation() {
    }

    public DeliveryInformation(DeliveryInformationDto deliveryInformationDto) {
        if (deliveryInformationDto.getRecipient().isBlank() || deliveryInformationDto.getAddress().isBlank()) {
            throw new InvalidDeliveryInformation();
        }

        this.recipient = deliveryInformationDto.getRecipient();
        this.address = deliveryInformationDto.getAddress();
        this.message = deliveryInformationDto.getMessage();
    }

    public DeliveryInformation(String recipient, String address, String message) {
        this.recipient = recipient;
        this.address = address;
        this.message = message;
    }

    public DeliveryInformationDto toDto() {
        return new DeliveryInformationDto(recipient, address, message);
    }

    public static DeliveryInformation fake() {
        return new DeliveryInformation("fake recipient", "fake address", "fake message");
    }
}
