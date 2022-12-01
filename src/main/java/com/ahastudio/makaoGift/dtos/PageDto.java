package com.ahastudio.makaoGift.dtos;

public class PageDto {
    private int current;
    private int total;

    public PageDto(int current, int totalPages) {
        this.current = current;
        this.total = totalPages;
    }

    public int getCurrent() {
        return current;
    }

    public int getTotal() {
        return total;
    }
}
