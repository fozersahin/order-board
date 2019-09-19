package com.silverbars.orderboard.service.impl;

import com.silverbars.orderboard.contstant.OrderType;
import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void addOrder() {

        orderService.addOrder(new Order("user1", 3.5, 350.0, OrderType.BUY));
        orderService.addOrder(new Order("user2", 3.5, 350.0, OrderType.SELL));

        assertEquals(1, orderService.getBuyOrders().size());
        assertEquals(1, orderService.getSellOrders().size());

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void removeOrder() {
        Order buyOrder = new Order("user1", 3.5, 350.0, OrderType.BUY);
        Order sellOrder = new Order("user2", 3.5, 350.0, OrderType.SELL);

        orderService.addOrder(buyOrder);
        orderService.addOrder(sellOrder);


        orderService.removeOrder(buyOrder);
        orderService.removeOrder(sellOrder);

        assertEquals(0, orderService.getBuyOrders().size());
        assertEquals(0, orderService.getSellOrders().size());

    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getBuyOrders() {

        assertEquals(0, orderService.getBuyOrders().size());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getSellOrders() {
        assertEquals(0, orderService.getSellOrders().size());
    }


}