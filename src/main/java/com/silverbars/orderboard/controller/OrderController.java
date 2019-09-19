package com.silverbars.orderboard.controller;


import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/registerOrder")
    public boolean registerOrder(@RequestParam Order order) {

        return orderService.addOrder(order);

    }

    @PostMapping("/cancelOrder")
    public boolean cancelOrder(@RequestParam Order order) {

        return orderService.removeOrder(order);

    }

    @GetMapping("/getOrders")
    public List<Order> getOrders() {

        return new ArrayList<>();

    }

}
