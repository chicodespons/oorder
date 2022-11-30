package com.swichfully.chicodespons.oorder.objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Order {

    private List<ItemGroup> itemGroupList;
    private double totalPrice;
    private String userEmail;

    public Order(List<ItemGroup> itemGroupList, double totalPrice, String userEmail) {
        this.itemGroupList = itemGroupList;
        this.totalPrice = totalPrice;
        this.userEmail = userEmail;
    }
}
