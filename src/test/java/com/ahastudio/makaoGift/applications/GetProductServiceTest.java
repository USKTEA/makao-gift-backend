package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.models.Product;
import com.ahastudio.makaoGift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
class GetProductServiceTest {
    ProductRepository productRepository;
    GetProductService getProductService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        getProductService = new GetProductService(productRepository);
    }

    @Test
    void list() {
        Product product = new Product(
                1L, "초콜릿", "Jocker", 10_000L, "yammy chocolate" ,"1"
        );

        Page<Product> page = new PageImpl<>(List.of(product));

        given(productRepository.findAll(any(Pageable.class))).willReturn(
                page
        );

        Page<Product> products = getProductService.list(1);

        assertThat(products.getTotalElements()).isEqualTo(1);
    }
}
