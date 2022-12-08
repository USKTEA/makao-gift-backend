package com.ahastudio.makaoGift.exceptions;

public class IncorrectSignUpName extends SignUpFailed {
    public IncorrectSignUpName() {
        super("이름을 다시 확인해주세요");
    }
}
