package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpMemberName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class MemberNameTest {

    @Test
    void creation() {
        assertDoesNotThrow(
                () -> new MemberName("ashal1234")
        );
    }

    @Test
    void whenContainsNonLowerAlphabetOrNumbers() {
        assertThrows(IncorrectSignUpMemberName.class, () -> {
            new MemberName("아샬입니다");
        });
    }

    @Test
    void whenContainsNonKoreanCharacters() {
        assertThrows(IncorrectSignUpMemberName.class, () -> {
            new MemberName("Ashal1234");
        });
    }

    @Test
    void whenMemberNameIsTooShort() {
        assertThrows(IncorrectSignUpMemberName.class, () -> {
            new MemberName("as1");
        });
    }

    @Test
    void whenMemberNameIsTooLong() {
        assertThrows(IncorrectSignUpMemberName.class, () -> {
            new MemberName("thisisashalforsure1234");
        });
    }

    @Test
    void equality() {
        MemberName memberName1 = new MemberName("ashal1234");
        MemberName memberName2 = new MemberName("ashal1234");

        assertThat(memberName1).isEqualTo(memberName2);
    }
}
