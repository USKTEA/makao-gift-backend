package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.LoginService;
import com.ahastudio.makaoGift.dtos.LoginRequestDto;
import com.ahastudio.makaoGift.dtos.LoginResultDto;
import com.ahastudio.makaoGift.exceptions.LoginFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        String memberName = loginRequestDto.getMemberName();
        String password = loginRequestDto.getPassword();

        Member member = loginService.login(memberName, password);

        String accessToken = jwtUtil.encode(member.memberName());

        return new LoginResultDto(
                accessToken,
                member.name(),
                member.amount()
        );
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Login Failed";
    }
}
