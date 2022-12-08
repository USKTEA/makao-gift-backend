package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.MemberDto;
import com.ahastudio.makaoGift.exceptions.AmountNotEnough;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private String name;
    private String memberName;
    private String encodedPassword;
    private Long amount;

    @ElementCollection
    private List<Long> orders;

    public Member() {
    }

    public Member(Long id, String memberName, String name, Long amount) {
        this.id = id;
        this.name = name;
        this.memberName = memberName;
        this.amount = amount;
        this.orders = new ArrayList<>();
    }

    public Member(String name, String memberName) {
        this.name = name;
        this.memberName = memberName;
    }

    public static Member fake(String memberName) {
        return new Member(1L, memberName, "김이박최아샬", 50000L);
    }

    public void order(Order order) {
        if (this.amount < order.cost()) {
            throw new AmountNotEnough();
        }

        this.amount -= order.cost();
        this.orders.add(order.id());
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }

    public void changePassword(String password,
                               PasswordEncoder passwordEncoder) {
        encodedPassword = passwordEncoder.encode(password);
    }

    public Long id() {
        return this.id;
    }

    public String name() {
        return name;
    }

    public String memberName() {
        return memberName;
    }

    public String encodedPassword() {
        return this.encodedPassword;
    }

    public Long amount() {
        return amount;
    }

    public int orderCounts() {
        return orders.size();
    }

    public MemberDto toDto() {
        return new MemberDto(memberName, name, amount);
    }

    public boolean isDuplicated(String memberName) {
        if (Objects.equals(this.memberName, memberName)) {
            return true;
        }

        return false;
    }

    public Member of(String name, String memberName) {
        return new Member(name, memberName);
    }

    public void increaseAmount(Long amount) {
        this.amount = amount;
    }
}
