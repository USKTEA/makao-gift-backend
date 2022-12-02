package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.LoginFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member login(String memberName, String password) {
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(() -> new LoginFailed());

        if (!member.authenticate(password, passwordEncoder)) {
            throw new LoginFailed();
        }

        return member;
    }
}
