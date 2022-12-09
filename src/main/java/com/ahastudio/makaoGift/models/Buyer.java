package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.BuyerDoesNotExists;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Buyer {
    @Column(name = "buyer")
    private String value;

    public Buyer(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new BuyerDoesNotExists();
        }

        this.value = value;
    }

    public Buyer() {
    }

    public String name() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Buyer buyer = (Buyer) other;

        return Objects.equals(value, buyer.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
