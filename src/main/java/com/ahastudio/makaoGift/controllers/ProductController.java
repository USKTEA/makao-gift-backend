package com.ahastudio.makaoGift.controllers;

import com.ahastudio.makaoGift.applications.GetProductService;
import com.ahastudio.makaoGift.dtos.PageDto;
import com.ahastudio.makaoGift.dtos.ProductsDto;
import com.ahastudio.makaoGift.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    private GetProductService getProductService;

    public ProductController(GetProductService getProductService) {
        this.getProductService = getProductService;
    }

    @GetMapping
    public ProductsDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<Product> found = getProductService.list(page);

        ProductsDto products = new ProductsDto(
                found.getContent()
                        .stream()
                        .map(Product::toDto).collect(Collectors.toList()));

        PageDto pageDto = new PageDto(page, found.getTotalPages());

        products.setPage(pageDto);

        return products;
    }
}
