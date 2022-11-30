package com.swichfully.chicodespons.oorder.service;

import com.swichfully.chicodespons.oorder.dtos.ItemDto;
import com.swichfully.chicodespons.oorder.mapper.ItemMapper;
import com.swichfully.chicodespons.oorder.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemDto addANewItem(ItemDto itemDto) {
        itemRepository.addItem(itemMapper.mapFromItemDtoToItem(itemDto));
        return itemDto;
    }
}
