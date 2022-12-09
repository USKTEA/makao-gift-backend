package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.MemberDto;
import com.ahastudio.makaoGift.exceptions.AmountNotEnough;

import javax.persistence.ElementCollection;
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
    private Name name;
    private MemberName memberName;
    private Password encodedPassword;
    private Long amount;

    @ElementCollection
    private List<Long> orders;

    public Member() {
    }

    public Member(Long id, MemberName memberName, Name name, Long amount) {
        this.id = id;
        this.name = name;
        this.memberName = memberName;
        this.amount = amount;
        this.orders = new ArrayList<>();
    }

    public Member(Name name, MemberName memberName) {
        this.name = name;
        this.memberName = memberName;
    }

    public static Member fake(MemberName memberName) {
        return new Member(1L, memberName, new Name("김이박최아샬"), 50000L);
    }

    public void order(Order order) {
        if (this.amount < order.cost()) {
            throw new AmountNotEnough();
        }

        this.amount -= order.cost();
        this.orders.add(order.id());
    }

    public void authenticate(Password password) {
        this.encodedPassword.match(password);
    }

    public void changePassword(Password password) {
        encodedPassword = password.encode();
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

    public Password encodedPassword() {
        return this.encodedPassword;
    }

    public Long amount() {
        return amount;
    }

    public int orderCounts() {
        return orders.size();
    }

    public MemberDto toDto() {
        return new MemberDto(memberName.value(), name.value(), amount);
    }

    public boolean isDuplicated(MemberName memberName) {
        if (Objects.equals(this.memberName, memberName)) {
            return true;
        }

        return false;
    }

    public Member of(Name name, MemberName memberName) {
        return new Member(name, memberName);
    }

    public void increaseAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object other) {
        Member otherMember = (Member) other;

        return Objects.equals(this.id, otherMember.id)
                && Objects.equals(this.memberName, otherMember.memberName);
    }
}
