package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class NameTest {

    @Test
    void creation() {
        assertDoesNotThrow(
                () -> new Name("김아샬")
        );
    }

    @Test
    void whenNameIsTooShort() {
        assertThrows(IncorrectSignUpName.class, () -> {
            new Name("김아");
        });
    }

    @Test
    void whenNameIsTooLong() {
        assertThrows(IncorrectSignUpName.class, () -> {
            new Name("김아샬입니다저의이름은");
        });
    }

    @Test
    void whenNameContainsNonKorean() {
        assertThrows(IncorrectSignUpName.class, () -> {
            new Name("myNameIsAshal1");
        });
    }
}
