package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.anotations.UtfEncoding;
import com.ahastudio.makaoGift.applications.GetProductService;
import com.ahastudio.makaoGift.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@UtfEncoding
@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductService getProductService;

    @Test
    void list() throws Exception {
        Product product = new Product(
                1L, "초콜릿", "Jocker", 10_000L, "yammy chocolate", "1"
        );

        Page<Product> products = new PageImpl<>(List.of(product));

        given(getProductService.list(1)).willReturn(
                products
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"products\":[")
                ));
    }

    @Test
    void product() throws Exception {
        given(getProductService.product(1L))
                .willReturn(Product.fake(1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ));
    }
}
