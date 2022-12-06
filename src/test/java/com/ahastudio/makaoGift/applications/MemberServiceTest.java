package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
class MemberServiceTest {
    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setup() {
        memberRepository = mock(MemberRepository.class);
        memberService = new MemberService(memberRepository);

        given(memberRepository.findByMemberName(any()))
                .willReturn(Optional.of(Member.fake("ashal1234")));
    }

    @Test
    void member() {
        String memberName = "ashal1234";

        Member member = memberService.detail(memberName);

        verify(memberRepository).findByMemberName(memberName);

        assertThat(member.memberName()).isEqualTo(memberName);
    }
}
