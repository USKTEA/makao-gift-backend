package com.ahastudio.makaoGift.exceptions;

public class IncorrectSignUpPassword extends SignUpFailed{
    public IncorrectSignUpPassword() {
        super("비밀번호를 다시 확인해주세요");
    }
}
