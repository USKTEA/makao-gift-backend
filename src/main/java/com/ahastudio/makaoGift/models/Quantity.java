package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.InvalidOrderQuantity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Quantity {
    @Column(name = "quantity")
    private Long value;

    public Quantity() {
    }

    public Quantity(Long value) {
        if (Objects.isNull(value)) {
            throw new InvalidOrderQuantity();
        }

        this.value = value;
    }

    public Long value() {
        return value;
    }
}
