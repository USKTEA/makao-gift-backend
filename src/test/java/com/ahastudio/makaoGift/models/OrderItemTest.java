package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.ProductDto;
import com.ahastudio.makaoGift.exceptions.OrderItemError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
class OrderItemTest {
    Long id;
    String name;
    String manufacturer;
    Long price;
    String description;
    String imageUrl;

    @BeforeEach
    void setup() {
        id = 1L;
        name = "Chocolate";
        manufacturer = "MnM";
        price = 1000L;
        description = "tasty";
        imageUrl = "testUrl";
    }

    @Test
    void whenCreationSuccess() {
        ProductDto productDto = new ProductDto(id, name, manufacturer, price, description, imageUrl);

        assertDoesNotThrow(() -> {
            OrderItem orderItem = new OrderItem(productDto);
        });
    }

    @Test
    void whenProductIdIsNull() {
        ProductDto productDto = new ProductDto(null, name, manufacturer, price, description, imageUrl);

        assertThrows(OrderItemError.class, () -> {
            OrderItem orderItem = new OrderItem(productDto);
        });
    }

    @Test
    void whenProductNameIsBlank() {
        ProductDto productDto = new ProductDto(id, "", manufacturer, price, description, imageUrl);

        assertThrows(OrderItemError.class, () -> {
            OrderItem orderItem = new OrderItem(productDto);
        });
    }

    @Test
    void whenProductManufacturerIsBlank() {
        ProductDto productDto = new ProductDto(id, name, "", price, description, imageUrl);

        assertThrows(OrderItemError.class, () -> {
            OrderItem orderItem = new OrderItem(productDto);
        });
    }

    @Test
    void whenProductPriceIsNull() {
        ProductDto productDto = new ProductDto(id, name, manufacturer, null, description, imageUrl);

        assertThrows(OrderItemError.class, () -> {
            OrderItem orderItem = new OrderItem(productDto);
        });
    }

    @Test
    void whenProductDescriptionIsBlank() {
        ProductDto productDto = new ProductDto(id, name, manufacturer, price, "", imageUrl);

        assertThrows(OrderItemError.class, () -> {
            OrderItem orderItem = new OrderItem(productDto);
        });
    }


    @Test
    void whenProductImageUrlIsBlank() {
        ProductDto productDto = new ProductDto(id, name, manufacturer, price, description, "");

        assertThrows(OrderItemError.class, () -> {
            OrderItem orderItem = new OrderItem(productDto);
        });
    }
}
