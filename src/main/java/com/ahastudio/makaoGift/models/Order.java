package com.ahastudio.makaoGift.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

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

    public Order(OrderNumber orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order(Long id) {
        this.id = id;
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

    public Order(Long id, Cost cost) {
        this.id = id;
        this.cost = cost;
    }

    public static Order fake(OrderNumber orderNumber) {
        return new Order(orderNumber);
    }

    public OrderNumber orderNumber() {
        return orderNumber;
    }

    public Long id() {
        return id;
    }

    public Order of(OrderNumber orderNumber,
                    Buyer buyer,
                    OrderItem orderItem,
                    Quantity quantity,
                    Cost cost,
                    DeliveryInformation deliveryInformation) {
        return new Order(orderNumber, buyer, orderItem, quantity, cost, deliveryInformation);
    }

    public Long cost() {
        return cost.amount();
    }
}
