package com.ahastudio.makaoGift.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BackdoorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void setupProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/backdoor/setup-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"products\":[" +
                                "{\"id\":1,\"name\":\"상품1\"," +
                                "\"manufacturer\":\"test\"," +
                                "\"price\":10000," +
                                "\"description\":\"1번째 상품\"}" +
                                "]" +
                                "}"))
                .andExpect(status().isCreated());
    }

}
