package com.phincon.order.api.service;

import com.phincon.order.api.model.Orders;
import com.phincon.order.api.model.OrdersDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    JmsTemplate jmsTemplate;

    Orders orderNew = new Orders();
    Flux<Orders> ordersFlux = Flux.empty();

    @JmsListener(destination = "queue.complete")
    public void completeOrder(Message<Orders> orders) {
        orderNew = orders.getPayload();
        log.info("Order completed: " + orders.getPayload());
    }

    @JmsListener(destination = "queue.failed")
    public void failedOrder(Message<Orders> orders) {
        orderNew = orders.getPayload();
        log.info("Order reverted: " + orders.getPayload());
    }

    @Override
    public Mono<Orders> createOrder(OrdersDto order) {
        orderNew.setId(order.getId());
        orderNew.setProductId(order.getProductId());
        orderNew.setPrice(order.getPrice());
        orderNew.setActionId(order.getActionId());
        jmsTemplate.convertAndSend("queue.order", orderNew);
        log.info("Order sent API: " + orderNew);
        return Mono.just(orderNew);
    }

    @JmsListener(destination = "queue.all.order")
    public void receiveMessage(Message<Orders> orders) {
        Orders order = orders.getPayload();
        log.info("Order received: " + order);
        ordersFlux = Flux.concat(ordersFlux, Mono.just(order));
    }

    @Override
    public Mono<Orders> getOrderById(String id) {
        return ordersFlux.filter(order -> order.getId().equals(id))
                .next()
                .doOnSuccess(order -> log.info("Order found: " + order));
    }
}
