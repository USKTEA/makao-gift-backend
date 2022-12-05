package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.BuyerDoestNotExists;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class BuyerTest {

    @Test
    void creation() {
        assertDoesNotThrow(() -> {
            Buyer buyer = new Buyer("ashal1234");
        });
    }

    @Test
    void whenThereIsNoBuyerValue() {
        assertThrows(BuyerDoestNotExists.class, () -> {
            Buyer buyer = new Buyer("");
        });
    }
}
