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

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String memberName;
    private String encodedPassword;
    private String name;
    private Long amount;

    @ElementCollection
    private List<Long> orders;

    public Member() {
    }

    public Member(String memberName, String name, Long amount) {
        this.memberName = memberName;
        this.name = name;
        this.amount = amount;
        this.orders = new ArrayList<>();
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

    public String memberName() {
        return memberName;
    }

    public String name() {
        return name;
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

    public static Member fake(String memberName) {
        return new Member(memberName, "김이박최아샬", 50000L);
    }
}
