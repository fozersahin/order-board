package com.silverbars.orderboard.service.impl;

import com.silverbars.orderboard.constant.OrderType;
import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.model.Summary;
import com.silverbars.orderboard.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void addOrder() {

        orderService.addOrder(new Order("user1", 3, 350, OrderType.BUY));
        orderService.addOrder(new Order("user2", 3, 350, OrderType.SELL));

        assertEquals(2, orderService.getSummary().size());

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void removeOrder() {
        Order buyOrder = new Order("user1", 4, 351, OrderType.BUY);
        Order sellOrder = new Order("user2", 5, 350, OrderType.SELL);

        orderService.addOrder(buyOrder);
        orderService.addOrder(sellOrder);


        orderService.removeOrder(buyOrder);
        orderService.removeOrder(sellOrder);

        assertEquals(0, orderService.getSummary().size());

    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getSummary() {

        orderService.addOrder(new Order("user1", 1, 349, OrderType.BUY));
        orderService.addOrder(new Order("user2", 4, 322, OrderType.SELL));
        orderService.addOrder(new Order("user3", 5, 380, OrderType.BUY));
        orderService.addOrder(new Order("user8", 1, 320, OrderType.SELL));
        orderService.addOrder(new Order("user8", 1, 320, OrderType.SELL));
        orderService.addOrder(new Order("user8", 1, 320, OrderType.SELL));

        List<Summary> summaryTestList = new ArrayList<>();
        summaryTestList.add(new Summary(OrderType.SELL, 320, 3 ));
        summaryTestList.add(new Summary(OrderType.SELL, 322, 4 ));
        summaryTestList.add(new Summary(OrderType.BUY, 380, 5 ));
        summaryTestList.add(new Summary(OrderType.BUY, 349, 1));


        assertEquals(summaryTestList, orderService.getSummary());
    }

}