package com.swichfully.chicodespons.oorder.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.swichfully.chicodespons.oorder.objects.Item;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class ItemGroupDto {

    private int itemId;
    private int amount;
    private Item item;
    private double totalPrice;
    private LocalDate shippingDate;

    @JsonCreator
    public ItemGroupDto(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

}
