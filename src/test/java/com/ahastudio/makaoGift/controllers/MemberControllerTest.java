package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.anotations.UtfEncoding;
import com.ahastudio.makaoGift.applications.CreateMemberService;
import com.ahastudio.makaoGift.applications.GetMemberService;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
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
    private GetMemberService getMemberService;

    @MockBean
    private CreateMemberService createMemberService;

    private MemberName memberName;

    @BeforeEach
    void setup() {
        memberName = new MemberName("ashal1234");
        given(getMemberService.detail(memberName))
                .willReturn(Member.fake(memberName));
    }

    @Test
    void member() throws Exception {
        String token = jwtUtil.encode(memberName.value());

        mockMvc.perform(MockMvcRequestBuilders.get("/members/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"??????????????????\"")
                ));
    }

    @Test
    void whenThereIsSameMemberName() throws Exception {
        given(getMemberService.count(memberName)).willReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/members?countOnly=true&memberName=" + memberName.value()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("1")
                ));
    }

    @Test
    void whenThereIsNoSameMemberName() throws Exception {
        given(getMemberService.count(memberName)).willReturn(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/members?countOnly=true&memberName=" + memberName.value()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("0")
                ));
    }

    @Test
    void whenRegisterSuccess() throws Exception {
        String name = "?????????";
        String password = "Password1234";

        given(createMemberService.create(any()))
                .willReturn(Member.fake(memberName));

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"" + name + "\"," +
                                " \"memberName\":\"" + memberName.value() + "\"," +
                                "\"password\":\"" + password + "\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ));
    }

    @Test
    void whenNameIsBlank() throws Exception {
        String name = "";
        String password = "Password1234";

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"" + name + "\"," +
                                " \"memberName\":\"" + memberName.value() + "\"," +
                                "\"password\":\"" + password + "\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenMemberNameIsBlank() throws Exception {
        String name = "?????????";
        String memberName = "";
        String password = "Password1234";

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"" + name + "\"," +
                                " \"memberName\":\"" + memberName + "\"," +
                                "\"password\":\"" + password + "\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPasswordIsBlank() throws Exception {
        String name = "?????????";
        String memberName = "tester1234";
        String password = "";

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"" + name + "\"," +
                                " \"memberName\":\"" + memberName + "\"," +
                                "\"password\":\"" + password + "\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
