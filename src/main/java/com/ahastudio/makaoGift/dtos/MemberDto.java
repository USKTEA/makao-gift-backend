package com.ahastudio.makaoGift.dtos;

public class MemberDto {
    private String name;
    private Long amount;

    public MemberDto(String name, Long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
