package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.InvalidOrderCost;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Cost {
    @Column(name = "cost")
    private Long value;

    public Cost() {
    }

    public Cost(Long value) {
        if (Objects.isNull(value)) {
            throw new InvalidOrderCost();
        }

        this.value = value;
    }

    public Long amount() {
        return value;
    }

    public Long value() {
        return value;
    }
}
