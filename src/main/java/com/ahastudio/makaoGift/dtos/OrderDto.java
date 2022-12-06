package com.ahastudio.makaoGift.dtos;

import java.time.LocalDateTime;

public class OrderDto {
    private Long id;
    private OrderItemDto orderItem;
    private Long quantity;
    private Long cost;
    private DeliveryInformationDto deliveryInformation;
    private LocalDateTime createdAt;

    public OrderDto() {
    }

    public OrderDto(Long id, OrderItemDto orderItem, Long quantity, Long cost,
                    DeliveryInformationDto deliveryInformation, LocalDateTime createdAt) {
        this.id = id;
        this.orderItem = orderItem;
        this.quantity = quantity;
        this.cost = cost;
        this.deliveryInformation = deliveryInformation;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public OrderItemDto getOrderItem() {
        return orderItem;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getCost() {
        return cost;
    }

    public DeliveryInformationDto getDeliveryInformation() {
        return deliveryInformation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

