package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.dtos.SignUpRequestDto;
import com.ahastudio.makaoGift.exceptions.MemberNameAlreadyInUse;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Name;
import com.ahastudio.makaoGift.models.Password;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateMemberService {
    private final MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public CreateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = new Argon2PasswordEncoder();
    }

    public Member create(SignUpRequestDto signUpRequestDto) {
        Name name = new Name(signUpRequestDto.getName());
        MemberName memberName = new MemberName(signUpRequestDto.getMemberName());
        Password password = new Password(signUpRequestDto.getPassword());

        Member member = memberRepository.findByMemberName(memberName)
                .orElse(new Member());

        if (member.isDuplicated(memberName)) {
            throw new MemberNameAlreadyInUse();
        }

        member = member.of(name, memberName);

        member.changePassword(password);

        member.increaseAmount(50000L);

        Member saved = memberRepository.save(member);

        //시간 나면 여기 값 객체 사용하게
        return saved;
    }
}
