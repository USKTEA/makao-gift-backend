package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.CreateOrderService;
import com.ahastudio.makaoGift.applications.GetOrderService;
import com.ahastudio.makaoGift.models.Buyer;
import com.ahastudio.makaoGift.models.Cost;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.models.OrderNumber;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import com.ahastudio.makaoGift.repositories.OrderRepository;
import com.ahastudio.makaoGift.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CreateOrderService createOrderService;

    @SpyBean
    private GetOrderService getOrderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private MemberRepository memberRepository;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void OrderSuccess() throws Exception {
        OrderNumber orderNumber = new OrderNumber("test");
        Buyer buyer = new Buyer("tester");
        Cost cost = new Cost(100L);
        Member member = new Member(buyer.name(), "Ashal", 50_000L);

        given(orderRepository.findByOrderNumber(orderNumber)).willReturn(
                Optional.of(new Order())
        );

        given(orderRepository.save(any())).willReturn(
                new Order(1L, cost)
        );

        given(memberRepository.findByMemberName(buyer.name()))
                .willReturn(Optional.of(member));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"orderNumber\": \"" + orderNumber.value() + "\"," +
                                "\"specification\": " +
                                "{" +
                                "\"buyer\": \"" + buyer.name() + "\"," +
                                "\"product\": " +
                                "{" +
                                "\"id\": 8888,\n" +
                                "\"name\": \"초콜릿\",\n" +
                                "\"manufacturer\": \"Faker\",\n" +
                                "\"price\": 8888,\n" +
                                "\"description\": \"yammy fake chocolate\",\n" +
                                "\"imageUrl\": 8888\n" +
                                "}," +
                                "\"quantity\": 1,\n" +
                                "\"cost\": 8888,\n" +
                                "\"deliveryInformation\": " +
                                "{" +
                                " \"recipient\": \"페이커\", " +
                                "\"address\": \"서울시 성동구 상원동\", " +
                                "\"message\": \"압도적 승리\" " +
                                "}" +
                                "}" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("id")
                ));
    }

    @Test
    void orderFailWhenOrderNumberIsBlank() throws Exception {
        OrderNumber orderNumber = new OrderNumber("test");

        given(orderRepository.findByOrderNumber(orderNumber)).willReturn(
                Optional.of(new Order(orderNumber))
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"orderNumber\": \"\"," +
                                "\"specification\": " +
                                "{" +
                                "\"buyer\": \"faker1234\"," +
                                "\"product\": " +
                                "{" +
                                "\"id\": 8888,\n" +
                                "\"name\": \"초콜릿\",\n" +
                                "\"manufacturer\": \"Faker\",\n" +
                                "\"price\": 8888,\n" +
                                "\"description\": \"yammy fake chocolate\",\n" +
                                "\"imageUrl\": 8888\n" +
                                "}," +
                                "\"quantity\": 1,\n" +
                                "\"cost\": 8888,\n" +
                                "\"deliveryInformation\": " +
                                "{" +
                                " \"recipient\": \"페이커\", " +
                                "\"address\": \"서울시 성동구 상원동\", " +
                                "\"message\": \"압도적 승리\" " +
                                "}" +
                                "}" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSpecificationIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"orderNumber\": \"46faee12-a885-4acb-8ee8-1ea9ff31f9bb\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void list() throws Exception {
        Order order = mock(Order.class);

        given(orderRepository.findByBuyer(any(), any()))
                .willReturn(new PageImpl<>(List.of(order)));

        String memberName = "ashal1234";
        String token = jwtUtil.encode(memberName);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"orders\":[")
                ));
    }

    @Test
    void whenMemberRequestHisOwnOrder() throws Exception {
        Long id = 1L;
        String memberName = "ashal1234";

        Buyer buyer = new Buyer("ashal1234");

        Order order = Order.fake(id, buyer);

        String token = jwtUtil.encode(memberName);

        given(orderRepository.findById(any()))
                .willReturn(Optional.of(order));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/" + id)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":" + id)
                ));
    }
}
