package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpPassword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
