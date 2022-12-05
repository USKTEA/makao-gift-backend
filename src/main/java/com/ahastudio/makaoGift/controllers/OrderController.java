package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.CreateOrderService;
import com.ahastudio.makaoGift.dtos.OrderRequestDto;
import com.ahastudio.makaoGift.dtos.OrderResultDto;
import com.ahastudio.makaoGift.exceptions.OrderRequestFailed;
import com.ahastudio.makaoGift.models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final CreateOrderService createOrderService;

    public OrderController(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResultDto order(
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
    public String requestOrderFail(Exception exception) {
        return exception.getMessage();
    }
}
