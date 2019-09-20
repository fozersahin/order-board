package com.silverbars.orderboard.controller;


import com.silverbars.orderboard.constant.OrderType;
import com.silverbars.orderboard.constant.Summary;
import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class OrderController {


    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/registerOrder")
    public boolean registerOrder(@RequestParam Order order) {

        return orderService.addOrder(order);

    }

    @PostMapping("/cancelOrder")
    public boolean cancelOrder(@RequestParam Order order) {

        return orderService.removeOrder(order);

    }

    @GetMapping("/getSummary")
    public List<Summary> getOrders() {

        return orderService.getSummary();

    }

    @GetMapping("/greeting")
    public Order greeting() {


        Order order = new Order("user1", 1, 349, OrderType.BUY);

        return order;
    }

}
