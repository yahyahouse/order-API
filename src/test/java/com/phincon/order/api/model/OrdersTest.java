package com.phincon.order.api.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersTest {

    @Test
    void OrdersTest() {
        Orders order = new Orders();
        order.setId("1");
        order.setProductId("1");
        order.setPrice(1000L);
        order.setActionId("1");
        order.setStatus("Completed");
        assertEquals("1", order.getId());
        assertEquals("1", order.getProductId());
        assertEquals(1000L, order.getPrice());
        assertEquals("1", order.getActionId());
        assertEquals("Completed", order.getStatus());
    }

}