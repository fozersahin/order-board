package com.silverbars.orderboard.model;

import com.silverbars.orderboard.contstant.OrderType;
import lombok.Data;


@Data
public class Order{

    private String userId;
    private double quantity;
    private Double price;
    private OrderType orderType;


    public Order(String userId, double quantity, Double price, OrderType orderType) {
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }
}
