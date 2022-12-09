package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.LoginService;
import com.ahastudio.makaoGift.exceptions.LoginFailed;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {

    private MemberName memberName;
    private Password password;
    private Password wrongPassword;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        memberName = new MemberName("ashal1234");
        password = new Password("Password1234!");

        wrongPassword = new Password("notPassword1234!");

        Member member = Member.fake(memberName);

        given(loginService.login(memberName, password))
                .willReturn(member);

        given(loginService.login(memberName, wrongPassword))
                .willThrow(new LoginFailed());
    }

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"memberName\":\"" + memberName.value() + "\"," +
                                " \"password\":\"" + password.number() + "\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"amount\"")
                ));
    }

    @Test
    void loginFailed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"memberName\":\"" + memberName.value() + "\"," +
                                " \"password\":\"" + wrongPassword.number() + "\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
