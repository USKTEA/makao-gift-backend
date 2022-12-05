package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.DeliveryInformationDto;
import com.ahastudio.makaoGift.exceptions.InvalidDeliveryInformation;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class DeliveryInformationTest {
    String recipient = "tester";
    String address = "testAddress";
    String message = "test message";

    @Test
    void creation() {
        DeliveryInformationDto deliveryInformationDto = new DeliveryInformationDto(recipient, address, message);

        assertDoesNotThrow(() -> {
            DeliveryInformation deliveryInformation = new DeliveryInformation(deliveryInformationDto);
        });
    }

    @Test
    void whenRecipientIsNotExist() {
        DeliveryInformationDto deliveryInformationDto = new DeliveryInformationDto("", address, message);

        assertThrows(InvalidDeliveryInformation.class, () -> {
            DeliveryInformation deliveryInformation = new DeliveryInformation(deliveryInformationDto);
        });
    }

    @Test
    void whenAddressIsNotExist() {
        DeliveryInformationDto deliveryInformationDto = new DeliveryInformationDto(recipient, "", message);

        assertThrows(InvalidDeliveryInformation.class, () -> {
            DeliveryInformation deliveryInformation = new DeliveryInformation(deliveryInformationDto);
        });
    }

}
