package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.OrderRequestFailed;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class OrderTest {

    @Test
    void whenIsOwnByRequestMember() {
        MemberName memberName = new MemberName("ashal1234");

        Buyer buyer = new Buyer(memberName.value());

        Order order = new Order(1L, buyer);

        assertDoesNotThrow(() -> {
            order.checkIsOwnBuy(memberName);
        });
    }

    @Test
    void whenIsNotOwnByRequestMember() {
        MemberName memberName = new MemberName("ashal1234");

        String otherMember = "notAshal1234";

        Buyer buyer = new Buyer(otherMember);

        Order order = new Order(1L, buyer);

        assertThrows(OrderRequestFailed.class, () -> {
            order.checkIsOwnBuy(memberName);
        });
    }
}
