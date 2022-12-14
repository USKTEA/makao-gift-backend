package com.ahastudio.makaoGift.dtos;

import javax.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank
    private String memberName;

    @NotBlank
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
