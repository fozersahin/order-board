package com.silverbars.orderboard.controller;


import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.model.Summary;
import com.silverbars.orderboard.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public boolean registerOrder(@Valid @RequestBody Order order) {

        return orderService.addOrder(order);

    }

    @PostMapping("/cancelOrder")
    public boolean cancelOrder(@Valid @RequestBody Order order) {

        return orderService.removeOrder(order);

    }

    @GetMapping("/getSummary")
    public List<Summary> getSummary() {

        return orderService.getSummary();

    }

}
