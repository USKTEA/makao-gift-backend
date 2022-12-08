package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.OrderNumberNotExist;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class OrderNumberTest {

    @Test
    void creation() {
        OrderNumber orderNumber = new OrderNumber("test");

        assertThat(orderNumber).isNotNull();
    }

    @Test
    void whenThereIsNoOrderNumberValue() {
        assertThrows(OrderNumberNotExist.class, () -> {
            new OrderNumber("");
        });
    }

    @Test
    void equality() {
        OrderNumber orderNumber1 = new OrderNumber("test");
        OrderNumber orderNumber2 = new OrderNumber("test");
        OrderNumber otherOrderNumber = new OrderNumber("notTest");

        assertThat(orderNumber1).isEqualTo(orderNumber2);
        assertThat(orderNumber1).isNotEqualTo(otherOrderNumber);
    }
}
