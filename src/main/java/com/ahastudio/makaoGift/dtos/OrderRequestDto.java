package com.ahastudio.makaoGift.dtos;

import com.ahastudio.makaoGift.models.OrderNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotBlank
    private String orderNumber;

    @NotNull
    private SpecificationDto specification;

    public OrderRequestDto() {
    }

    public OrderRequestDto(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderRequestDto(String orderNumber, SpecificationDto specification) {
        this.orderNumber = orderNumber;
        this.specification = specification;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public SpecificationDto getSpecification() {
        return specification;
    }

    public static OrderRequestDto fake(OrderNumber orderNumber) {
        ProductDto productDto = new ProductDto(1L, "초콜릿", "menufacturer", 1L, "yammy", "1");
        DeliveryInformationDto deliveryInformationDto = new DeliveryInformationDto("recipient", "address" ,"message");
        SpecificationDto specificationDto = new SpecificationDto("tester", productDto, 1L, 1L, deliveryInformationDto);

        return new OrderRequestDto(orderNumber.value(), specificationDto);
    }
}
