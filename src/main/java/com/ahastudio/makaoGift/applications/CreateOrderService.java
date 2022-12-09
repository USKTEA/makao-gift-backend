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
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.models.Order;
import com.ahastudio.makaoGift.models.OrderItem;
import com.ahastudio.makaoGift.models.OrderNumber;
import com.ahastudio.makaoGift.models.Quantity;
import com.ahastudio.makaoGift.repositories.MemberRepository;
import com.ahastudio.makaoGift.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

        if (orderRepository.existsByOrderNumberAndBuyer(orderNumber, buyer)) {
            throw new OrderAlreadyExists();
        }

        Member member = memberRepository.findByMemberName(new MemberName(buyer.name()))
                .orElseThrow(MemberNotFound::new);

        OrderItem orderItem = new OrderItem(specificationDto.getProduct());
        Quantity quantity = new Quantity(specificationDto.getQuantity());
        Cost cost = new Cost(specificationDto.getCost());

        DeliveryInformationDto deliveryInformationDto = specificationDto.getDeliveryInformation();

        DeliveryInformation deliveryInformation = new DeliveryInformation(deliveryInformationDto);

        Order order = Order.of(orderNumber, buyer, orderItem, quantity, cost, deliveryInformation);

        Order saved = orderRepository.save(order);

        member.order(saved);

        return saved;
    }
}
