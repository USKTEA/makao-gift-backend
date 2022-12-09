package com.ahastudio.makaoGift.utils;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class JwtUtilTest {
    static final String secret = "SECRET";
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        jwtUtil = new JwtUtil(secret);
    }

    @Test
    void encodeAndDecode() {
        String original = "ashal1234";

        String token = jwtUtil.encode(original);

        assertThat(token).contains(".");

        String memberName = jwtUtil.decode(token);

        assertThat(memberName).isEqualTo(original);
    }

    @Test
    void decodeError() {
        assertThrows(JWTDecodeException.class, () -> {
            jwtUtil.decode("xxx");
        });
    }
}
