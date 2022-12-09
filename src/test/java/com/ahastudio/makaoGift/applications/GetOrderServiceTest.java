package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.OrderRequestFailed;
import com.ahastudio.makaoGift.models.Buyer;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        MemberName memberName = new MemberName("ashal1234");

        Order order = mock(Order.class);

        Page<Order> page = new PageImpl<>(List.of(order));

        given(orderRepository.findByBuyer(any(), any()))
                .willReturn(page);

        Page<Order> orders = getOrderService.list(memberName, 1);

        assertThat(orders.getTotalElements()).isEqualTo(1);
    }

    @Test
    void whenMemberRequestItsOwnOrder() {
        Long id = 1L;
        MemberName memberName = new MemberName("ashal1234");

        Buyer buyer = new Buyer(memberName.value());

        Order order = new Order(id, buyer);

        given(orderRepository.findById(id))
                .willReturn(Optional.of(order));

        Order found = getOrderService.order(memberName, id);

        assertThat(found.id()).isEqualTo(id);
    }

    @Test
    void whenThereIsNoOrder() {
        Long id = 9999999L;
        MemberName memberName = new MemberName("ashal1234");

        given(orderRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(OrderRequestFailed.class, () -> {
            getOrderService.order(memberName, id);
        });
    }

    @Test
    void whenOrderIsNotOwnByMember() {
        Long id = 1L;
        MemberName memberName = new MemberName("ashal1234");

        Buyer buyer = new Buyer("notAshal1234");

        Order order = new Order(id, buyer);

        given(orderRepository.findById(id))
                .willReturn(Optional.of(order));

        assertThrows(OrderRequestFailed.class, () -> {
            getOrderService.order(memberName, id);
        });
    }

}
