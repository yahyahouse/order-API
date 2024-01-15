package com.phincon.order.api.service;

import com.phincon.order.api.model.Orders;
import com.phincon.order.api.model.OrdersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class OrderServiceImplTest {
    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private OrderServiceImpl orderService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void completeOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Orders order = new Orders();
        order.setStatus("Completed");
        Message<Orders> message = new GenericMessage<>(order);
        orderService.completeOrder(message);
        assertEquals("Completed", orderService.orderNew.getStatus());
    }

    @Test
    void failedOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Orders order = new Orders();
        order.setStatus("Failed");
        Message<Orders> message = new GenericMessage<>(order);
        orderService.failedOrder(message);
        assertEquals("Failed", orderService.orderNew.getStatus());
    }

    @Test
    void createOrder() {
        OrdersDto orderDto = new OrdersDto("1", "1", 1000L, "1");
        Mockito.doNothing().when(jmsTemplate).convertAndSend(any(String.class), any(Orders.class));
        Mono<Orders> resultMono = orderService.createOrder(orderDto);
        StepVerifier.create(resultMono)
                .expectSubscription()
                .expectNextMatches(order -> true)
                .verifyComplete();
        verify(jmsTemplate).convertAndSend("queue.order", orderService.orderNew);
    }
}