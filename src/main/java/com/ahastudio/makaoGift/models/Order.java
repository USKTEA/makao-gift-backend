package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.OrderDto;
import com.ahastudio.makaoGift.exceptions.OrderRequestFailed;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private OrderNumber orderNumber;

    @Embedded
    private Buyer buyer;

    @Embedded
    private OrderItem orderItem;

    @Embedded
    private Quantity quantity;

    @Embedded
    private Cost cost;

    @Embedded
    private DeliveryInformation deliveryInformation;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }

    public Order(OrderNumber orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order(Long id, Cost cost) {
        this.id = id;
        this.cost = cost;
    }

    public Order(Long id, Buyer fake) {
        this.id = id;
        this.buyer = fake;
    }

    public Order(OrderNumber orderNumber,
                 Buyer buyer,
                 OrderItem orderItem,
                 Quantity quantity,
                 Cost cost,
                 DeliveryInformation deliveryInformation) {
        this.orderNumber = orderNumber;
        this.buyer = buyer;
        this.orderItem = orderItem;
        this.quantity = quantity;
        this.cost = cost;
        this.deliveryInformation = deliveryInformation;
    }

    public Order(Long id, Buyer buyer, OrderItem orderItem, Quantity quantity, Cost cost, DeliveryInformation deliveryInformation) {
        this.id = id;
        this.buyer = buyer;
        this.orderItem = orderItem;
        this.quantity = quantity;
        this.cost = cost;
        this.deliveryInformation = deliveryInformation;
    }

    public boolean isDuplicated(OrderNumber orderNumber, Buyer buyer) {
        if (Objects.equals(this.orderNumber, orderNumber) && Objects.equals(this.buyer, buyer)) {
            return true;
        }

        return false;
    }

    public void checkIsOwnBuy(String memberName) {
        if (!Objects.equals(buyer.name(), memberName)) {
            throw new OrderRequestFailed();
        }
    }

    public OrderNumber orderNumber() {
        return orderNumber;
    }

    public Long id() {
        return id;
    }

    public Long cost() {
        return cost.amount();
    }

    public static Order fake(Long id, Buyer buyer) {
        return new Order(id, buyer, OrderItem.fake(), new Quantity(1L), new Cost(1L), DeliveryInformation.fake());
    }

    public static Order fake(OrderNumber orderNumber, Buyer buyer) {
        return new Order(orderNumber, buyer, null, null, null, null);
    }

    public static Order fake(OrderNumber orderNumber) {
        return new Order(orderNumber);
    }

    public Order of(OrderNumber orderNumber,
                    Buyer buyer,
                    OrderItem orderItem,
                    Quantity quantity,
                    Cost cost,
                    DeliveryInformation deliveryInformation) {
        return new Order(orderNumber, buyer, orderItem, quantity, cost, deliveryInformation);
    }

    public OrderDto toDto() {
        return new OrderDto(id,
                orderItem.toDto(),
                quantity.value(),
                cost.value(),
                deliveryInformation.toDto(),
                createdAt
                );
    }
}
