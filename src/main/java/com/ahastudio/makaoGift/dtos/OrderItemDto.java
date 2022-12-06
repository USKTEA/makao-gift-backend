package com.ahastudio.makaoGift.dtos;

public class OrderItemDto {
    private Long id;
    private String name;
    private String manufacturer;
    private Long price;
    private String description;
    private String imageUrl;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, String name, String manufacturer,
                        Long price, String description, String imageUrl) {
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
}
