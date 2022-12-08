package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.dtos.SignUpRequestDto;
import com.ahastudio.makaoGift.exceptions.SignUpFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
class CreateMemberServiceTest {
    private CreateMemberService createMemberService;
    private MemberRepository memberRepository;

    @BeforeEach
    void setup() {
        memberRepository = mock(MemberRepository.class);
        createMemberService = new CreateMemberService(memberRepository);
    }

    @Test
    void createSuccess() {
        String name = "김아샬";
        String memberName = "ashal1234";
        String password = "Password1234!";

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(name, memberName, password);

        given(memberRepository.findByMemberName(memberName))
                .willReturn(Optional.empty());

        createMemberService.create(signUpRequestDto);

        verify(memberRepository).save(any());
    }

    @Test
    void createFailed() {
        String name = "xx";
        String memberName = "xx";
        String password = "xx";

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(name, memberName, password);

        assertThrows(SignUpFailed.class, () -> {
            createMemberService.create(signUpRequestDto);
        });
    }

    @Test
    void whenMemberNameAlreadyInUse() {
        String name = "김아샬";
        String memberName = "ashal1234";
        String password = "Password1234!";

        Member member = Member.fake(memberName);

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(name, memberName, password);

        given(memberRepository.findByMemberName(memberName))
                .willReturn(Optional.of(member));

        assertThrows(SignUpFailed.class, () -> {
            createMemberService.create(signUpRequestDto);
        });
    }
}
