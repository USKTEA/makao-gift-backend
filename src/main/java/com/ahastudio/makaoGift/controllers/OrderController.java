package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.CreateOrderService;
import com.ahastudio.makaoGift.applications.GetOrderService;
import com.ahastudio.makaoGift.dtos.OrderDto;
import com.ahastudio.makaoGift.dtos.OrderRequestDto;
import com.ahastudio.makaoGift.dtos.OrderResultDto;
import com.ahastudio.makaoGift.dtos.OrdersDto;
import com.ahastudio.makaoGift.dtos.PageDto;
import com.ahastudio.makaoGift.exceptions.OrderCreateFailed;
import com.ahastudio.makaoGift.exceptions.OrderRequestFailed;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final CreateOrderService createOrderService;
    private final GetOrderService getOrderService;

    public OrderController(CreateOrderService createOrderService, GetOrderService getOrderService) {
        this.createOrderService = createOrderService;
        this.getOrderService = getOrderService;
    }

    @GetMapping
    public OrdersDto list(
            @RequestAttribute MemberName memberName,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<Order> found = getOrderService.list(memberName, page);

        OrdersDto orders = new OrdersDto(
                found.getContent()
                        .stream()
                        .map(Order::toDto).collect(Collectors.toList())
        );

        PageDto pageDto = new PageDto(page, found.getTotalPages());

        orders.setPage(pageDto);

        return orders;
    }

    @GetMapping("{id}")
    public OrderDto order(
            @RequestAttribute("memberName") MemberName memberName,
            @PathVariable Long id
    ) {
        Order order = getOrderService.order(memberName, id);

        return order.toDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResultDto create(
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        Order order = createOrderService.create(orderRequestDto);

        return new OrderResultDto(order.id());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String requestOrderNotExists() {
        return "주문 요청에 실패했습니다";
    }

    @ExceptionHandler(OrderRequestFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidOrderRequest() {
        return "잘못된 요청입니다";
    }

    @ExceptionHandler(OrderCreateFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String orderCreateFail(Exception exception) {
        return exception.getMessage();
    }
}
