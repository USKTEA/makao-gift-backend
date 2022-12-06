package com.ahastudio.makaoGift.dtos;

import java.util.List;

public class OrdersDto {
    public List<OrderDto> orders;
    private PageDto page;

    public OrdersDto() {
    }

    public OrdersDto(List<OrderDto> orders) {
        this.orders = orders;
    }

    public void setPage(PageDto page) {
        this.page = page;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public PageDto getPage() {
        return page;
    }
}
