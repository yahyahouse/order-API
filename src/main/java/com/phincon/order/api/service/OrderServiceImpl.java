package com.phincon.order.api.service;

import com.phincon.order.api.model.Orders;
import com.phincon.order.api.model.OrdersDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    JmsTemplate jmsTemplate;

    Orders orderNew = new Orders();

    @JmsListener(destination = "queue.complete")
    public void completeOrder(Message<Orders> orders) {
        orderNew.setStatus(orders.getPayload().getStatus());
        log.info("Order completed: " + orders.getPayload());
    }

    @JmsListener(destination = "queue.failed")
    public void failedOrder(Message<Orders> orders) {
        orderNew.setStatus(orders.getPayload().getStatus());
        log.info("Order reverted: " + orders.getPayload());
    }

    @Override
    public Mono<Orders> createOrder(OrdersDto order){
        orderNew.setId(order.getId());
        orderNew.setProductId(order.getProductId());
        orderNew.setPrice(order.getPrice());
        orderNew.setActionId(order.getActionId());
        jmsTemplate.convertAndSend("queue.order", orderNew);
        log.info("Order sent API: " + orderNew);
        return Mono.delay(Duration.ofSeconds(5)).flatMap(orderLimit -> {
            if (orderNew.getStatus() == null) {
                orderNew.setStatus("Pending");
            }
            return Mono.just(orderNew);
        });
    }
}
