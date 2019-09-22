package com.silverbars.orderboard.controller;


import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.model.Summary;
import com.silverbars.orderboard.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/register")
    public boolean registerOrder(@Valid @RequestBody Order order) {

        return orderService.addOrder(order);

    }

    @PostMapping("/cancel")
    public boolean cancelOrder(@Valid @RequestBody Order order) {

        return orderService.removeOrder(order);

    }

    @GetMapping("/summary")
    public List<Summary> getSummary() {

        return orderService.getSummary();

    }

}
