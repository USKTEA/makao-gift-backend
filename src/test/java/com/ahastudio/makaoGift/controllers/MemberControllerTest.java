package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.anotations.UtfEncoding;
import com.ahastudio.makaoGift.applications.MemberService;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@UtfEncoding
@WebMvcTest(MemberController.class)
@ActiveProfiles("test")
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private JwtUtil jwtUtil;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    void setup() {
        given(memberService.detail(any()))
                .willReturn(Member.fake("ashal1234"));
    }

    @Test
    void member() throws Exception {
        String token = jwtUtil.encode("ashal1234");

        mockMvc.perform(MockMvcRequestBuilders.get("/members/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"김이박최아샬\"")
                ));
    }
}
