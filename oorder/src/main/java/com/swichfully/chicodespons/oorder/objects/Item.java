package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.exceptions.StockAmountException;
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
    private int id;
    private static int idCounter = 0;

    public Item(String name, String description, double price, int stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        setStockAmount(stockAmount);
        this.id = idCounter;
        idCounter++;
    }

    public void setStockAmount(int stockAmount) {
        if(stockAmount<=0){
            throw new StockAmountException("The stockAmount can't be 0 or negative");
        } else
            this.stockAmount = stockAmount;
    }
}
