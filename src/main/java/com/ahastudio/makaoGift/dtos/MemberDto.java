package com.ahastudio.makaoGift.dtos;

public class MemberDto {
    private String memberName;
    private String name;
    private Long amount;

    public MemberDto(String memberName,String name, Long amount) {
        this.memberName = memberName;
        this.name = name;
        this.amount = amount;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
