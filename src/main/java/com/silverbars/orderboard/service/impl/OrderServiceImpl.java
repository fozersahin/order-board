package com.silverbars.orderboard.service.impl;

import com.silverbars.orderboard.contstant.OrderType;
import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private List<Order> orderBuyList = new ArrayList<>();
    private List<Order> orderSellList = new ArrayList<>();


    @Override
    public boolean addOrder(Order order) {

        if (OrderType.BUY.equals(order.getOrderType())) {
            orderBuyList.add(order);
        } else {
            orderSellList.add(order);
        }
        return true;
    }

    @Override
    public boolean removeOrder(Order order) {

        if (OrderType.BUY.equals(order.getOrderType())) {
            orderBuyList.remove(order);
        } else {
            orderSellList.remove(order);
        }
        return true;
    }

    @Override
    public List<Order> getBuyOrders() {
        return orderBuyList;
    }

    @Override
    public List<Order> getSellOrders() {
        return orderSellList;
    }

}
