package com.phincon.order.api.controller;

import com.phincon.order.api.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
    }
}
