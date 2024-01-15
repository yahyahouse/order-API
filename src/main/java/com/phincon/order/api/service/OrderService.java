package com.phincon.order.api.service;


import com.phincon.order.api.model.Orders;
import com.phincon.order.api.model.OrdersDto;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Orders> createOrder(OrdersDto order) throws InterruptedException;
}
