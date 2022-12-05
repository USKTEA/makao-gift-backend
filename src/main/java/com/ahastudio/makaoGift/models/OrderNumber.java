package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.OrderNumberNotExist;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class OrderNumber {
    @Column(name = "order_number")
    private String value;

    public OrderNumber() {
    }

    public OrderNumber(String value) {
        if (value.isBlank()) {
            throw new OrderNumberNotExist();
        }

        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        OrderNumber that = (OrderNumber) object;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
