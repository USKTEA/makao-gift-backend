package com.ahastudio.makaoGift.exceptions;

public class MemberNotFound extends RuntimeException {
    public MemberNotFound() {
        super("멤버정보를 찾을 수 없습니다");
    }
}
