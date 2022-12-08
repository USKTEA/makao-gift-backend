package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.InvalidOrderQuantity;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class QuantityTest {

    @Test
    void creation() {
        assertDoesNotThrow(() -> {
            new Quantity(1L);
        });
    }

    @Test
    void createWithoutValue() {
        assertThrows(InvalidOrderQuantity.class, () -> {
            new Quantity(null);
        });
    }
}
