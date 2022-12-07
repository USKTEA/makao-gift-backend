package com.ahastudio.makaoGift.applications;

import com.ahastudio.makaoGift.dtos.DeliveryInformationDto;
import com.ahastudio.makaoGift.dtos.OrderRequestDto;
import com.ahastudio.makaoGift.dtos.ProductDto;
import com.ahastudio.makaoGift.dtos.SpecificationDto;
import com.ahastudio.makaoGift.exceptions.MemberNotFound;
import com.ahastudio.makaoGift.exceptions.OrderAlreadyExists;
import com.ahastudio.makaoGift.models.Buyer;
import com.ahastudio.makaoGift.models.Cost;
import com.ahastudio.makaoGift.models.DeliveryInformation;
import com.ahastudio.makaoGift.models.Member;
import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.models.OrderItem;
import com.ahastudio.makaoGift.models.OrderNumber;
import com.ahastudio.makaoGift.models.Quantity;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import com.ahastudio.makaoGift.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CreateOrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public CreateOrderService(OrderRepository orderRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    public Order create(OrderRequestDto orderRequestDto) {
        OrderNumber orderNumber = new OrderNumber(orderRequestDto.getOrderNumber());
        SpecificationDto specificationDto = orderRequestDto.getSpecification();
        Buyer buyer = new Buyer(specificationDto.getBuyer());

        Order order = orderRepository.findByOrderNumber(orderNumber).orElse(new Order());

        if (order.isDuplicated(orderNumber, buyer)) {
            throw new OrderAlreadyExists();
        }

        ProductDto productDto = specificationDto.getProduct();

        DeliveryInformationDto deliveryInformationDto = specificationDto.getDeliveryInformation();

        OrderItem orderItem = new OrderItem(productDto);
        Quantity quantity = new Quantity(specificationDto.getQuantity());
        Cost cost = new Cost(specificationDto.getCost());
        DeliveryInformation deliveryInformation = new DeliveryInformation(deliveryInformationDto);

        order = order.of(orderNumber, buyer, orderItem, quantity, cost, deliveryInformation);

        Order saved = orderRepository.save(order);

        Member member = memberRepository.findByMemberName(buyer.name())
                .orElseThrow(MemberNotFound::new);

        member.order(saved);

        return saved;
    }
}
