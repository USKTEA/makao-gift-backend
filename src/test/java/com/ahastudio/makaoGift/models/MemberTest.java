package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.AmountNotEnough;
import com.ahastudio.makaoGift.exceptions.MatchPasswordFailed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class MemberTest {
    private MemberName memberName;
    private Name name;
    private Money amount;

    @BeforeEach
    void setUp() {
        memberName = new MemberName("ashal1234");
        name = new Name("김아샬");
        amount = new Money(50000L);
    }

    @Test
    void authenticateSuccess() {
        Password password = new Password("Password1234!");

        Member member = Member.fake(memberName);

        member.changePassword(password);

        assertDoesNotThrow(() -> member.authenticate(password));
    }

    @Test
    void authenticateFail() {
        Member member = Member.fake(memberName);

        Password password = new Password("Password1234!");

        member.changePassword(password);

        Password other = new Password("notPassword1234!");

        assertThrows(MatchPasswordFailed.class, () -> {
            member.authenticate(other);
        });
    }

    @Test
    void orderWhenHaveEnoughAmount() {
        Long id = 1L;
        Cost cost = new Cost(1000L);

        Member member = new Member(1L, memberName, name, amount);

        Order order = new Order(id, cost);

        assertDoesNotThrow(() -> member.order(order));

        assertThat(member.orderCounts()).isEqualTo(1);
    }

    @Test
    void orderWhenDontHaveEnoughAmount() {
        Long id = 1L;
        Money amount = new Money(500L);
        Cost cost = new Cost(1000L);

        Member member = new Member(1L, memberName, name, amount);

        Order order = new Order(id, cost);

        assertThrows(AmountNotEnough.class, () -> {
            member.order(order);
        });
    }

    @Test
    void equality() {
        Member member1 = Member.fake(memberName);
        Member member2 = Member.fake(memberName);

        assertThat(member1).isEqualTo(member2);
    }
}
