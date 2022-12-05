package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.BuyerDoestNotExists;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Buyer {

    @Column(name = "buyer")
    private String value;

    public Buyer(String value) {
        if (value.isBlank()) {
            throw new BuyerDoestNotExists();
        }

        this.value = value;
    }

    public Buyer() {
    }

    public String name() {
        return value;
    }
}
