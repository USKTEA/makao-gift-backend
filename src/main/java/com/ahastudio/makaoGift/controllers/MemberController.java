package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.MemberService;
import com.ahastudio.makaoGift.dtos.MemberDto;
import com.ahastudio.makaoGift.models.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("me")
    public MemberDto login(
            @RequestAttribute("memberName") String memberName
    ) {
        Member member = memberService.detail(memberName);

        return member.toDto();
    }
}
