package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.BuyerDoestNotExists;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void equality() {
        Buyer buyer1 = new Buyer("tester");
        Buyer buyer2 = new Buyer("tester");

        assertThat(buyer1).isEqualTo(buyer2);
    }
}
