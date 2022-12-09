package com.ahastudio.makaoGift.models;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class ProductTest {

    @Test
    void equality() {
        Long id = 1L;

        Product product1 = Product.fake(id);
        Product product2 = Product.fake(id);

        assertThat(product1).isEqualTo(product2);
    }
}
