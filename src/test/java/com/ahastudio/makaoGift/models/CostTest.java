package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.InvalidOrderCost;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class CostTest {

    @Test
    void creation() {
        assertDoesNotThrow(() -> {
            Cost cost = new Cost(1L);

        });
    }

    @Test
    void whenThereIsNoValue() {
        assertThrows(InvalidOrderCost.class, () -> {
            Cost cost = new Cost(null);
        });
    }
}
