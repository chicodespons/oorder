package com.swichfully.chicodespons.oorder.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ItemDto {

    private String name;
    private String description;
    private double price;
    private int stockAmount;

    public ItemDto(String name, String description, double price, int stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }
}
