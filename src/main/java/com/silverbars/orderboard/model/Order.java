package com.silverbars.orderboard.model;

import com.silverbars.orderboard.constant.OrderType;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class Order {

    @NotNull
    private final String userId;

    @NotNull
    private final int quantity;

    @NotNull
    private final int price;

    @NotNull
    private final OrderType orderType;


    public Order(String userId, int quantity, int price, OrderType orderType) {
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + userId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderType=" + orderType +
                '}';
    }
}
