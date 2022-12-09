package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.AmountNotEnough;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @Test
    void creation() {
        Long amount = 1000L;

        Money money = new Money(amount);

        assertThat(money.amount()).isEqualTo(amount);
    }

    @Test
    void decrease() {
        Long amount = 1000L;

        Money money = new Money(amount);

        assertThat(money.decrease(new Money(100L))).isEqualTo(new Money(900L));
    }

    @Test
    void whenAmountIsNotEnough() {
        Long amount = 500L;

        Money money = new Money(amount);
        Money cost = new Money(amount + 1000L);

        assertThrows(AmountNotEnough.class, () -> money.decrease(cost));
    }

    @Test
    void equality() {
        Money money1 = new Money(1000L);
        Money money2 = new Money(1000L);

        assertThat(money1).isEqualTo(money2);
    }
}
