package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.AmountNotEnough;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class MemberTest {

    @Test
    void authenticate() {
        Member member = Member.fake("ashal1234");
        String password = "Password1234!";

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        member.changePassword(password, passwordEncoder);

        assertThat(member.authenticate(password, passwordEncoder)).isTrue();
        assertThat(member.authenticate("xxx", passwordEncoder)).isFalse();
    }

    @Test
    void orderWhenHaveEnoughAmount() {
        Long amount = 10000L;
        Long id = 1L;
        Cost cost = new Cost(1000L);

        Member member = new Member("ashal1234", "Ashal", amount);

        Order order = new Order(id, cost);

        member.order(order);

        assertThat(member.amount()).isEqualTo(amount - cost.amount());
        assertThat(member.orderCounts()).isEqualTo(1);
    }

    @Test
    void orderWhenDontHaveEnoughAmount() {
        Long amount = 500L;
        Long id = 1L;
        Cost cost = new Cost(1000L);

        Member member = new Member("ashal1234", "Ashal", amount);

        Order order = new Order(id, cost);

        assertThrows(AmountNotEnough.class, () -> {
            member.order(order);
        });
    }
}
