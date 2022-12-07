package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.ProductDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String manufacturer;
    private Long price;
    private String description;
    private String imageUrl;

    public Product() {
    }

    public Product(long id, String name, String manufacturer, long price, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public ProductDto toDto() {
        return new ProductDto(id, name, manufacturer, price, description, imageUrl);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Product product = (Product) other;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, price, description, imageUrl);
    }

    public static Product fake(Long id) {
        return new Product(id, "초콜릿", "Jocker", 10_000L, "yammy chocolate", "1");
    }
}
