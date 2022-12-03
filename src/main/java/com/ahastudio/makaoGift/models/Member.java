package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.MemberDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String memberName;
    private String encodedPassword;
    private String name;
    private Long amount;

    public Member() {
    }

    public Member(String memberName, String name, long amount) {
        this.memberName = memberName;
        this.name = name;
        this.amount = amount;
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

    public static Member fake(String memberName) {
        return new Member(memberName, "김이박최아샬", 50000L);
    }

    public MemberDto toDto() {
        return new MemberDto(memberName, name, amount);
    }
}
