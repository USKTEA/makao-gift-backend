package com.ahastudio.makaoGift.exceptions;

public class LoginFailed extends RuntimeException {
    public LoginFailed() {
        super("아이디 혹은 비밀번호가 맞지 않습니다");
    }
}
