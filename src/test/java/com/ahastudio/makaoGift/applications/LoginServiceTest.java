package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.LoginFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Password;
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
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = mock(MemberRepository.class);
        loginService = new LoginService(memberRepository);
    }

    @Test
    void loginSuccess() {
        MemberName memberName = new MemberName("ashal1234");
        Password password = new Password("Password1234!");

        Member member = Member.fake(memberName);

        member.changePassword(password);

        given(memberRepository.findByMemberName(memberName))
                .willReturn(Optional.of(member));

        Member found = loginService.login(memberName, password);

        assertThat(found.memberName()).isEqualTo(memberName);
    }

    @Test
    void loginWithIncorrectMemberName() {
        assertThrows(LoginFailed.class, () ->
                loginService.login(new MemberName("notashal1234"), new Password("Password1234!")));
    }

    @Test
    void loginWithIncorrectPassword() {
        assertThrows(LoginFailed.class, () ->
                loginService.login(new MemberName("ashal1234"), new Password("notPassword1234!")));
    }
}
