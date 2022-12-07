package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.OrderItemDto;
import com.ahastudio.makaoGift.dtos.ProductDto;
import com.ahastudio.makaoGift.exceptions.OrderItemError;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class OrderItem {

    @Column(name = "product_id")
    private Long id;
    private String name;
    private String manufacturer;
    private Long price;
    private String description;
    private String imageUrl;

    public OrderItem() {
    }

    public OrderItem(Long id, String name, String manufacturer,
                     Long price, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public OrderItem(ProductDto productDto) {
        if (Objects.isNull(productDto.getId()) || Objects.isNull(productDto.getPrice())) {
            throw new OrderItemError();
        }

        if (productDto.getName().isBlank() || productDto.getManufacturer().isBlank()
                || productDto.getManufacturer().isBlank() || productDto.getDescription().isBlank()
        || productDto.getImageUrl().isBlank()) {
            throw new OrderItemError();
        }

        this.id = productDto.getId();
        this.name = productDto.getName();
        this.manufacturer = productDto.getManufacturer();
        this.price = productDto.getPrice();
        this.description = productDto.getDescription();
        this.imageUrl = productDto.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrderItemDto toDto() {
        return new OrderItemDto(id, name, manufacturer, price, description, imageUrl);
    }

    public static OrderItem fake() {
        return new OrderItem(1L, "fakeItem", "fakeManufacturer", 100L, "fake", "fake");
    }
}
