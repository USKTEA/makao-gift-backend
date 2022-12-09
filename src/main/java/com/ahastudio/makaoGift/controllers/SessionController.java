package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.LoginService;
import com.ahastudio.makaoGift.dtos.LoginRequestDto;
import com.ahastudio.makaoGift.dtos.LoginResultDto;
import com.ahastudio.makaoGift.exceptions.LoginFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Money;
import com.ahastudio.makaoGift.models.Name;
import com.ahastudio.makaoGift.models.Password;
import com.ahastudio.makaoGift.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("session")
public class SessionController {
    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public SessionController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        MemberName memberName = new MemberName(loginRequestDto.getMemberName());
        Password password = new Password(loginRequestDto.getPassword());

        Member member = loginService.login(memberName, password);

        Name name = member.name();
        Money amount = member.amount();

        String accessToken = jwtUtil.encode(memberName.value());

        return new LoginResultDto(
                accessToken,
                name.value(),
                amount.amount()
        );
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed(Exception exception) {
        return exception.getMessage();
    }
}
