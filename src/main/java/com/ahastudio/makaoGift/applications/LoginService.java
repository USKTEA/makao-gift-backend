package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.LoginFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Password;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member login(MemberName memberName, Password password) {
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(LoginFailed::new);

        member.authenticate(password);

        return member;
    }
}
