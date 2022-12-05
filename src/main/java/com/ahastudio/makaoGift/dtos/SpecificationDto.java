package com.ahastudio.makaoGift.dtos;

public class SpecificationDto {
    private String buyer;
    private ProductDto product;
    private Long quantity;
    private Long cost;
    private DeliveryInformationDto deliveryInformation;

    public SpecificationDto() {
    }

    public SpecificationDto(String buyer,
                            ProductDto product,
                            long quantity,
                            long cost,
                            DeliveryInformationDto deliveryInformation) {
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
        this.deliveryInformation = deliveryInformation;
    }

    public String getBuyer() {
        return buyer;
    }

    public ProductDto getProduct() {
        return product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getCost() {
        return cost;
    }

    public DeliveryInformationDto getDeliveryInformation() {
        return deliveryInformation;
    }
}
