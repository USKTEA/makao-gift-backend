package com.ahastudio.makaoGift.dtos;

public class LoginRequestDto {
    private String memberName;
    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String memberName, String password) {
        this.memberName = memberName;
        this.password = password;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPassword() {
        return password;
    }
}
