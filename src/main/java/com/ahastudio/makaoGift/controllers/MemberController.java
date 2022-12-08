package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.CreateMemberService;
import com.ahastudio.makaoGift.applications.GetMemberService;
import com.ahastudio.makaoGift.dtos.MemberCountDto;
import com.ahastudio.makaoGift.dtos.MemberDto;
import com.ahastudio.makaoGift.dtos.SignUpRequestDto;
import com.ahastudio.makaoGift.dtos.SignUpResultDto;
import com.ahastudio.makaoGift.models.Member;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("members")
public class MemberController {
    private final GetMemberService getMemberService;
    private CreateMemberService createMemberService;

    public MemberController(GetMemberService getMemberService, CreateMemberService createMemberService) {
        this.getMemberService = getMemberService;
        this.createMemberService = createMemberService;
    }

    @GetMapping("me")
    public MemberDto login(
            @RequestAttribute("memberName") String memberName
    ) {
        Member member = getMemberService.detail(memberName);

        return member.toDto();
    }

    @GetMapping
    public MemberCountDto count(@RequestParam boolean countOnly, String memberName) {
        if (countOnly) {
            return new MemberCountDto(getMemberService.count(memberName));
        }

        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResultDto signUp(
            @Valid @RequestBody SignUpRequestDto signUpRequestDto
    ) {
        Member member = createMemberService.create(signUpRequestDto);

        return new SignUpResultDto(member.id());
    }
}
