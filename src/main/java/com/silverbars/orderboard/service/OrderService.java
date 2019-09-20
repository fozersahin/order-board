package com.silverbars.orderboard.service;

import com.silverbars.orderboard.constant.Summary;
import com.silverbars.orderboard.model.Order;

import java.util.List;

public interface OrderService {

    boolean addOrder(Order order);

    boolean removeOrder(Order order);

    List<Summary> getSummary();
}
