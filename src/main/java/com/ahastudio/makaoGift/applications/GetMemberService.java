package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.MemberNotFound;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMemberService {
    private final MemberRepository memberRepository;

    public GetMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member detail(MemberName memberName) {
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(MemberNotFound::new);

        return member;
    }

    public Integer count(MemberName memberName) {
        List<Member> members = memberRepository.findAllByMemberName(memberName);

        return members.size();
    }
}
