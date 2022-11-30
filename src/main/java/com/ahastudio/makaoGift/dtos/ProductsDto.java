package com.ahastudio.makaoGift.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> products;

    public ProductsDto(List<ProductDto> productDtos) {
        this.products = productDtos;
    }

    public ProductsDto() {
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
