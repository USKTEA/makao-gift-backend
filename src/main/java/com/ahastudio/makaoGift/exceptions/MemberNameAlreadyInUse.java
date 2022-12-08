package com.ahastudio.makaoGift.exceptions;

public class MemberNameAlreadyInUse extends SignUpFailed {
    public MemberNameAlreadyInUse() {
        super("해당 아이디는 사용할 수 없습니다");
    }
}
