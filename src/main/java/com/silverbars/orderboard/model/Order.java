package com.silverbars.orderboard.model;

import com.silverbars.orderboard.constant.OrderType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Order implements Serializable {

    @NotNull
    private String userId;

    @NotNull
    private int quantity;

    @NotNull
    private int price;

    @NotNull
    private OrderType orderType;


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
