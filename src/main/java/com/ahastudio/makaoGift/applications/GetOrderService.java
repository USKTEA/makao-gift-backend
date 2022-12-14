package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.exceptions.OrderRequestFailed;
import com.ahastudio.makaoGift.models.Buyer;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GetOrderService {
    private final OrderRepository orderRepository;

    public GetOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Order> list(MemberName memberName, int page) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, 8, sort);
        Buyer buyer = new Buyer(memberName.value());

        Page<Order> orders = orderRepository.findByBuyer(buyer, pageable);

        return orders;
    }

    public Order order(MemberName memberName, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderRequestFailed::new);

        order.checkIsOwnBuy(memberName);

        return order;
    }
}
