package com.phincon.order.api.controller;

import com.phincon.order.api.model.Orders;
import com.phincon.order.api.model.OrdersDto;
import com.phincon.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Mono<Orders> createOrder(@RequestBody OrdersDto order) throws InterruptedException {
        return orderService.createOrder(order);
    }


}
