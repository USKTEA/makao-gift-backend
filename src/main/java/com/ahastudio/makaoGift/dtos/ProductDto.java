package com.ahastudio.makaoGift.dtos;

public class ProductDto {
    private Long id;
    private String name;
    private String manufacturer;
    private Long amount;
    private String description;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String manufacturer, Long amount, String description) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.amount = amount;
        this.description = description;
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

    public Long getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
