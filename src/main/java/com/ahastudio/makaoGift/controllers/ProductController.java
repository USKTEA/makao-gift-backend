package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.ProductService;
import com.ahastudio.makaoGift.dtos.ProductDto;
import com.ahastudio.makaoGift.dtos.ProductsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto list() {
        List<ProductDto> productDtos =
                productService.list()
                        .stream()
                        .map((product) -> product.toDto())
                        .collect(Collectors.toList());

        return new ProductsDto(productDtos);
    }
}
