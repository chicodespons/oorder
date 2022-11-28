package com.swichfully.chicodespons.oorder.objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Item {

    private String name;
    private String description;
    private double price;
    private int stockAmount;
    private String id;

    public Item(String name, String description, double price, int stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.id = UUID.randomUUID().toString();
    }
}
