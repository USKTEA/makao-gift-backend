package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.dtos.ProductDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public ProductDto toDto() {
        return new ProductDto(id, name, manufacturer, price, description, imageUrl);
    }
}
