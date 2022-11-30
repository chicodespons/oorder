package com.swichfully.chicodespons.oorder.repository;

import com.swichfully.chicodespons.oorder.objects.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class ItemRepository {

    private final Map<String, Item> itemMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public ItemRepository(){
        mockData();
    }

    private void mockData() {
        Item firstItem = new Item("Playdow",
                "plasticine om mee te spelen", 10.99,
                10);

        Item secondItem = new Item("Bubble blazer",
                "Bubbles childproof om te blazen",
                5.25, 15);
    }

    public void addItem(Item item){
        if(!itemMap.containsKey(item.getName())){
            itemMap.put(item.getName(), item);
        } else
            throw new IllegalArgumentException("Item allready in map");
    }




}
