package com.silverbars.orderboard.model;

import com.silverbars.orderboard.constant.OrderType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Summary implements Serializable {

    private OrderType type;
    private int price;
    private float quantity;


    public Summary(OrderType type, int price, float quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return type + " " + quantity + "kg" + " for " + price + "£";
    }
}
