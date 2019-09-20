package com.silverbars.orderboard.constant;

import lombok.Data;

@Data
public class Summary {

    private final OrderType type;
    private final int price;
    private final float quantity;

    @Override
    public String toString() {
        return  type +" "+ quantity + "kg" +" for " + price + "£";
    }
}
