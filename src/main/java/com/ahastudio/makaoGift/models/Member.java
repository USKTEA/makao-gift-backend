package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.MemberDto;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private MemberName memberName;

    @Embedded
    private Password encodedPassword;

    @Embedded
    private Money amount;

    @ElementCollection
    private List<Long> orders;

    public Member() {
    }

    public Member(Long id, MemberName memberName, Name name, Money amount) {
        this.id = id;
        this.name = name;
        this.memberName = memberName;
        this.amount = amount;
        this.orders = new ArrayList<>();
    }

    public Member(Name name, MemberName memberName, Password password) {
        this.name = name;
        this.memberName = memberName;
        this.encodedPassword = password.encode();
    }

    public void order(Order order) {
        this.amount = this.amount.decrease(new Money(order.cost()));

        this.orders.add(order.id());
    }

    public void authenticate(Password password) {
        this.encodedPassword.match(password);
    }

    public void changePassword(Password password) {
        encodedPassword = password.encode();
    }

    public void increaseAmount(Money amount) {
        this.amount = amount;
    }

    public Long id() {
        return this.id;
    }

    public Name name() {
        return name;
    }

    public MemberName memberName() {
        return memberName;
    }

    public Money amount() {
        return amount;
    }

    public int orderCounts() {
        return orders.size();
    }

    public MemberDto toDto() {
        return new MemberDto(memberName.value(), name.value(), amount.amount());
    }

    public static Member of(Name name, MemberName memberName, Password password) {
        return new Member(name, memberName, password);
    }

    public static Member fake(MemberName memberName) {
        return new Member(1L, memberName, new Name("김이박최아샬"), new Money(50000L));
    }


    @Override
    public boolean equals(Object other) {
        Member otherMember = (Member) other;

        return Objects.equals(this.id, otherMember.id)
                && Objects.equals(this.memberName, otherMember.memberName);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
