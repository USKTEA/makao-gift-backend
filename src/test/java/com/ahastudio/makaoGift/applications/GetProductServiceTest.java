package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.MemberNotFound;
import com.ahastudio.makaoGift.models.Product;
import com.ahastudio.makaoGift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
class GetProductServiceTest {
    private ProductRepository productRepository;
    private GetProductService getProductService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        getProductService = new GetProductService(productRepository);

        given(productRepository.save(any()))
                .willReturn(Product.fake(1L));

        given(productRepository.findById(1L))
                .willReturn(Optional.of(Product.fake(1L)));

        given(productRepository.findById(9999999999999L))
                .willThrow(new MemberNotFound());
    }

    @Test
    void list() {
        Product product = new Product(
                1L, "초콜릿", "Jocker", 10_000L, "yammy chocolate", "1"
        );

        Page<Product> page = new PageImpl<>(List.of(product));

        given(productRepository.findAll(any(Pageable.class))).willReturn(
                page
        );

        Page<Product> products = getProductService.list(1);

        assertThat(products.getTotalElements()).isEqualTo(1);
    }

    @Test
    void whenHaveProduct() {
        Long id = 1L;

        Product saved = productRepository.save(Product.fake(id));

        Product found = getProductService.product(id);

        assertThat(saved).isEqualTo(found);
    }

    @Test
    void whenDontHaveProduct() {
        assertThrows(MemberNotFound.class, () -> {
            getProductService.product(9999999999999L);
        });
    }
}
