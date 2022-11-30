package com.swichfully.chicodespons.oorder.mapper;

import com.swichfully.chicodespons.oorder.dtos.ItemDto;
import com.swichfully.chicodespons.oorder.objects.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item mapFromItemDtoToItem(ItemDto itemDto) {

        return new Item(itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getPrice(), itemDto.getStockAmount());
    }
}
