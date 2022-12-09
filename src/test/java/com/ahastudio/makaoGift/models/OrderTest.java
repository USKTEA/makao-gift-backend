package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.OrderRequestFailed;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class OrderTest {

    @Test
    void isDuplicated() {
        OrderNumber orderNumber = new OrderNumber("test");
        Buyer buyer = new Buyer("tester");

        Order order = Order.fake(orderNumber, buyer);

        assertThat(order.isDuplicated(orderNumber, buyer)).isTrue();
    }

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
