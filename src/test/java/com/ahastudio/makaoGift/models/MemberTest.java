package com.ahastudio.makaoGift.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void authenticate() {
        Member member = Member.fake("ashal1234");
        String password = "Password1234!";

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        member.changePassword(password, passwordEncoder);

        assertThat(member.authenticate(password, passwordEncoder)).isTrue();
        assertThat(member.authenticate("xxx", passwordEncoder)).isFalse();
    }
}
