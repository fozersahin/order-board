package com.silverbars.orderboard.service.impl;

import com.silverbars.orderboard.constant.OrderType;
import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.model.Summary;
import com.silverbars.orderboard.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;

@Service
public class OrderServiceImpl implements OrderService {


    private List<Order> orderList = new ArrayList<>();

    @Override
    public boolean addOrder(Order order) {
        return orderList.add(order);
    }

    @Override
    public boolean removeOrder(Order order) {
        return orderList.remove(order);
    }


    @Override
    public List<Summary> getSummary() {
        List<Summary> result = new ArrayList<>();

        result.addAll(createSummary(OrderType.SELL, naturalOrder()));
        result.addAll(createSummary(OrderType.BUY, reverseOrder()));

        return result;
    }

    //This method sums and sorts orders by their price.
    private List<Summary> createSummary(OrderType orderType, Comparator<Integer> comparator) {
        return orderList.stream()
                .filter(o -> o.getOrderType() == orderType)
                .collect(groupingBy(
                        Order::getPrice,
                        () -> new TreeMap<>(comparator),
                        summingInt(Order::getQuantity)))
                .entrySet().stream()
                .map(e -> new Summary(orderType, e.getKey(), e.getValue()))
                .collect(toList());
    }

}
