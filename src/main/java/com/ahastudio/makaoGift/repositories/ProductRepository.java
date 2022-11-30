package com.ahastudio.makaoGift.repositories;

import com.ahastudio.makaoGift.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unchecked")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
