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

    public Product(Long id, String name, String manufacturer, Long price, String description) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
    }

    public Product() {
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

    public ProductDto toDto() {
        return new ProductDto(id, name, manufacturer, price, description);
    }
}
