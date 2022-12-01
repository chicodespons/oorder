package com.swichfully.chicodespons.oorder.repository;

import com.swichfully.chicodespons.oorder.objects.Item;
import com.swichfully.chicodespons.oorder.objects.ItemGroup;
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
                "plasticine om mee te spelen", 10.00,
                10);

        Item secondItem = new Item("Bubble blazer",
                "Bubbles childproof om te blazen",
                5.00, 15);

        Item thirdItem = new Item("velo",
                "daar kan je op rijden",
                400.00,40);

        itemMap.put(firstItem.getName(), firstItem);
        itemMap.put(secondItem.getName(),secondItem);
        itemMap.put(thirdItem.getName(), thirdItem);
    }

    public void addItem(Item item){
        if(!itemMap.containsKey(item.getName())){
            itemMap.put(item.getName(), item);
        } else
            throw new IllegalArgumentException("Item allready in map");
    }

    public Item getItem(String key){
        return itemMap.get(key);
    }


    public Item getItemById(int itemId) {

       return itemMap.values().stream()
               .filter(item -> item.getId()==itemId)
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Item for given Id not found"));
    }

    public void updateStock(ItemGroup itemGroup) {
        String name = itemGroup.getItem().getName();

        int amountToSubtract = itemGroup.getAmount();
        int oldStockAmount = getItem(name).getStockAmount();
        int newStockAmount = oldStockAmount - amountToSubtract;

        String description = itemGroup.getItem().getDescription();
        double price = itemGroup.getItem().getPrice();

        Item updatedItem = new Item(name, description, price,
                newStockAmount);
        updatedItem.setId(getItem(name).getId());

        itemMap.put(name, updatedItem);
    }

    public void updateItem(Item itemToUpdate, String name) {

        if(itemMap.containsKey(name)){
            if(name.equals(itemToUpdate.getName())){
                itemMap.put(name, itemToUpdate);
            }else {
                itemMap.remove(name);
                itemMap.put(itemToUpdate.getName(), itemToUpdate);
            }
        } else
            throw new IllegalArgumentException("No item found in itemMap for given namekey");

    }
}
