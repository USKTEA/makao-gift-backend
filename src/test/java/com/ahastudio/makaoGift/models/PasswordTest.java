package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpPassword;
import com.ahastudio.makaoGift.exceptions.MatchPasswordFailed;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

    @Test
    void creation() {
        assertDoesNotThrow(() -> {
            new Password("Password1234!");
        });
    }

    @Test
    void whenPasswordIsTooShort() {
        assertThrows(IncorrectSignUpPassword.class, () -> {
            new Password("Pass12!");
        });
    }

    @Test
    void whenNotIncludesLowerClass() {
        assertThrows(IncorrectSignUpPassword.class, () -> {
            new Password("PASSWORD1234!");
        });
    }

    @Test
    void whenNotIncludesUpperClass() {
        assertThrows(IncorrectSignUpPassword.class, () -> {
            new Password("password1234!");
        });
    }

    @Test
    void whenNotIncludesNumber() {
        assertThrows(IncorrectSignUpPassword.class, () -> {
            new Password("password!!!!!");
        });
    }

    @Test
    void whenNotIncludesSpecialSymbol() {
        assertThrows(IncorrectSignUpPassword.class, () -> {
            new Password("password1234");
        });
    }

    @Test
    void whenPasswordsMatch() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        Password password1 = new Password(passwordEncoder.encode("Password1234!"));
        Password password2 = new Password("Password1234!");

        assertDoesNotThrow(() -> {
            password1.match(password2);
        });
    }

    @Test
    void whenPasswordDoNotMatch() {
        Password password1 = new Password("Password1234!");
        Password password2 = new Password("Password123!!!!!");

        assertThrows(MatchPasswordFailed.class, () -> {
            password1.match(password2);
        });
    }

    @Test
    void equality() {
        Password password1 = new Password("Password1234!");
        Password password2 = new Password("Password1234!");

        assertThat(password2).isEqualTo(password1);
    }
}
