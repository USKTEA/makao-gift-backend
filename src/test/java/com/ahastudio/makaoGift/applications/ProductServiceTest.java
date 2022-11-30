package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.models.Product;
import com.ahastudio.makaoGift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
class ProductServiceTest {
    ProductRepository productRepository;
    ProductService  productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void list() {
        Product product = new Product(
                1L, "초콜릿", "Jocker", 10_000L, "yammy chocolate"
        );

        given(productRepository.findAll()).willReturn(
                List.of(product)
        );

        List<Product> products = productService.list();

        assertThat(products).hasSize(1);
    }
}
