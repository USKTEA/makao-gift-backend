package com.ahastudio.makaoGift.dtos;

import javax.validation.constraints.NotBlank;

public class SignUpRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String memberName;

    @NotBlank
    private String password;

    public SignUpRequestDto(String name, String memberName, String password) {
        this.name = name;
        this.memberName = memberName;
        this.password = password;
    }

    public SignUpRequestDto() {
    }

    public String getName() {
        return name;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPassword() {
        return password;
    }
}
