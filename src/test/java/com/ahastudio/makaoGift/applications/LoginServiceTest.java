package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.dtos.LoginRequestDto;
import com.ahastudio.makaoGift.exceptions.LoginFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
class LoginServiceTest {
    private LoginService loginService;
    private PasswordEncoder passwordEncoder;
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = mock(MemberRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();

        loginService = new LoginService(memberRepository, passwordEncoder);
    }

    @Test
    void loginSuccess() {
        String memberName = "ashal1234";
        String password = "Password1234!";

        Member member = Member.fake(memberName);

        member.changePassword(password, passwordEncoder);

        given(memberRepository.findByMemberName(memberName))
                .willReturn(Optional.of(member));

        Member found = loginService.login(memberName, password);

        assertThat(found.memberName()).isEqualTo(memberName);
    }

    @Test
    void loginWithIncorrectMemberName() {
        assertThrows(LoginFailed.class, () ->
                loginService.login("xxx", "Password1234!"));
    }

    @Test
    void loginWithIncorrectPassword() {
        assertThrows(LoginFailed.class, () ->
                loginService.login("ashal1234", "xxx"));
    }
}
