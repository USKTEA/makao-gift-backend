package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.MemberNotFound;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member detail(String memberName) {
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(MemberNotFound::new);

        return member;
    }
}
