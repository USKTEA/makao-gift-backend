package com.ahastudio.makaoGift.repositories;

import com.ahastudio.makaoGift.models.Buyer;
import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.models.OrderNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@SuppressWarnings("unchecked")
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(OrderNumber orderNumber);

    Order save(Order order);

    Page<Order> findByBuyer(Buyer buyer, Pageable pageable);
}
