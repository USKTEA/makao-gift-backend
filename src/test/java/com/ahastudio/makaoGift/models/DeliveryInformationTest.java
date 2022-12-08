package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.DeliveryInformationDto;
import com.ahastudio.makaoGift.exceptions.InvalidDeliveryInformation;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class DeliveryInformationTest {
    private String recipient = "tester";
    private String address = "testAddress";
    private String message = "test message";

    @Test
    void creation() {
        DeliveryInformationDto deliveryInformationDto = new DeliveryInformationDto(recipient, address, message);

        assertDoesNotThrow(() -> {
            new DeliveryInformation(deliveryInformationDto);
        });
    }

    @Test
    void whenRecipientIsNotExist() {
        DeliveryInformationDto deliveryInformationDto = new DeliveryInformationDto("", address, message);

        assertThrows(InvalidDeliveryInformation.class, () -> {
            new DeliveryInformation(deliveryInformationDto);
        });
    }

    @Test
    void whenAddressIsNotExist() {
        DeliveryInformationDto deliveryInformationDto = new DeliveryInformationDto(recipient, "", message);

        assertThrows(InvalidDeliveryInformation.class, () -> {
            new DeliveryInformation(deliveryInformationDto);
        });
    }

}
