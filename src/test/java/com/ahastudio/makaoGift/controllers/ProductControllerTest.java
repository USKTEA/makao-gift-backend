package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.anotations.UtfEncoding;
import com.ahastudio.makaoGift.applications.ProductService;
import com.ahastudio.makaoGift.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@UtfEncoding
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ProductService productService;

    @Test
    void list() throws Exception {
        Product product = new Product(
                1L, "초콜릿", "Jocker", 10_000L, "yammy chocolate"
        );

        given(productService.list()).willReturn(
                List.of(product)
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"products\":[")
                ));
    }
}
