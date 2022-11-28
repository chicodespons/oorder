package com.swichfully.chicodespons.oorder.objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class ItemGroup {

    private Item item;
    private int amount;
    private double totalPrice;
    private LocalDate shippingDate;

    public ItemGroup(Item item, int amount, double totalPrice, LocalDate shippingDate) {
        this.item = item;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.shippingDate = shippingDate;
    }
}
