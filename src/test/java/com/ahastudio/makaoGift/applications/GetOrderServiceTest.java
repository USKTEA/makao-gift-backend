package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
class GetOrderServiceTest {
    private GetOrderService getOrderService;
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        getOrderService = new GetOrderService(orderRepository);
    }

    @Test
    void list() {
        String buyer = "ashal1234";

        Order order = mock(Order.class);

        Page<Order> page = new PageImpl<>(List.of(order));

        given(orderRepository.findByBuyer(any(),any()))
                .willReturn(page);

        Page<Order> orders = getOrderService.list(buyer, 1);

        assertThat(orders.getTotalElements()).isEqualTo(1);
    }
}
