package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.dtos.SignUpRequestDto;
import com.ahastudio.makaoGift.exceptions.MemberNameAlreadyInUse;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Money;
import com.ahastudio.makaoGift.models.Name;
import com.ahastudio.makaoGift.models.Password;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateMemberService {
    private final MemberRepository memberRepository;

    public CreateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(SignUpRequestDto signUpRequestDto) {
        Name name = new Name(signUpRequestDto.getName());
        MemberName memberName = new MemberName(signUpRequestDto.getMemberName());
        Password password = new Password(signUpRequestDto.getPassword());

        if (memberRepository.existsByMemberName(memberName)) {
            throw new MemberNameAlreadyInUse();
        }

        Member member = Member.of(name, memberName, password);

        member.increaseAmount(new Money(50000L));

        Member saved = memberRepository.save(member);

        return saved;
    }
}
