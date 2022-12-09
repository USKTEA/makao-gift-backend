package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.AmountNotEnough;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Money {
    @Column(name = "amount")
    private Long amount;

    public Money() {
    }

    public Money(long amount) {
        this.amount = amount;
    }

    public Long amount() {
        return amount;
    }

    public Money decrease(Money money) {
        if (amount < money.amount) {
            throw new AmountNotEnough();
        }
        
        return new Money(amount - money.amount());
    }

    @Override
    public boolean equals(Object other) {
        Money otherMoney = (Money) other;

        return Objects.equals(this.amount, otherMoney.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
