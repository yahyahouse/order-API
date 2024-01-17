package com.phincon.order.api.controller;

import com.phincon.order.api.model.Orders;
import com.phincon.order.api.model.OrdersDto;
import com.phincon.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public Mono<Orders> createOrder(@RequestBody OrdersDto order) throws InterruptedException {
        return orderService.createOrder(order);
    }

    @GetMapping("/get-orders/{id}")
    public Mono<Orders> getOrder(@PathVariable String id) {
        return orderService.getOrderById(id);
    }


}
