package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.dtos.OrderRequestDto;
import com.ahastudio.makaoGift.exceptions.OrderAlreadyExists;
import com.ahastudio.makaoGift.models.Buyer;
import com.ahastudio.makaoGift.models.Cost;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.models.OrderNumber;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import com.ahastudio.makaoGift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
class CreateOrderServiceTest {
    private CreateOrderService createOrderService;
    private OrderRepository orderRepository;
    private MemberRepository memberRepository;
    private Member member;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        memberRepository = mock(MemberRepository.class);
        member = mock(Member.class);
        createOrderService = new CreateOrderService(orderRepository, memberRepository);
    }

    @Test
    void whenCreateOrderSuccess() {
        OrderNumber orderNumber = new OrderNumber("test");
        Buyer buyer = new Buyer("tester");
        Order order = new Order();
        Cost cost = new Cost(1000L);
        Order saved = new Order(1L, cost);

        OrderRequestDto orderRequestDto = OrderRequestDto.fake(orderNumber);

        given(orderRepository.findByOrderNumber(orderNumber))
                .willReturn(Optional.of(order));

        given(orderRepository.save(any())).willReturn(saved);

        given(memberRepository.findByMemberName(buyer.name()))
                .willReturn(Optional.of(member));

        assertDoesNotThrow(() -> {
            createOrderService.create(orderRequestDto);
        });

        verify(orderRepository).save(any());

        verify(member).order(saved);
    }

    @Test
    void whenOrderIsAlreadyExists() {
        OrderNumber orderNumber = new OrderNumber("test");
        OrderRequestDto orderRequestDto = new OrderRequestDto(orderNumber.value());

        Order order = Order.fake(orderNumber);

        given(orderRepository.findByOrderNumber(orderNumber))
                .willReturn(Optional.of(order));

        assertThrows(OrderAlreadyExists.class, () -> {
            createOrderService.create(orderRequestDto);
        });
    }

}
