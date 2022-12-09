package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
class GetMemberServiceTest {
    private MemberRepository memberRepository;
    private GetMemberService getMemberService;
    private MemberName memberName;

    @BeforeEach
    void setup() {
        memberName = new MemberName("ashal1234");

        memberRepository = mock(MemberRepository.class);
        getMemberService = new GetMemberService(memberRepository);

        given(memberRepository.findByMemberName(any()))
                .willReturn(Optional.of(Member.fake(memberName)));

        given(memberRepository.findAllByMemberName(any()))
                .willReturn(List.of(new Member()));
    }

    @Test
    void member() {
        Member member = getMemberService.detail(memberName);

        verify(memberRepository).findByMemberName(memberName);

        assertThat(member.memberName()).isEqualTo(memberName);
    }

    @Test
    void count() {
        Integer count = getMemberService.count(memberName);

        verify(memberRepository).findAllByMemberName(memberName);

        assertThat(count).isEqualTo(1);
    }
}
