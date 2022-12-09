package com.ahastudio.makaoGift.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> products;
    private PageDto page;

    public ProductsDto() {
    }

    public ProductsDto(List<ProductDto> productDtos) {
        this.products = productDtos;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public PageDto getPage() {
        return page;
    }

    public void setPage(PageDto pageDto) {
        this.page = pageDto;
    }
}
