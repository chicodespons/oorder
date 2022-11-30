package com.swichfully.chicodespons.oorder.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.swichfully.chicodespons.oorder.objects.Customer;
import com.swichfully.chicodespons.oorder.objects.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
public class OrderDto {

    private List<ItemGroupDto> itemGroupDtosList;
    private double totalPrice;
    private String userEmail;

    @JsonCreator
    public OrderDto(List<ItemGroupDto> itemGroupDtosList) {
        this.itemGroupDtosList = itemGroupDtosList;
    }
}
