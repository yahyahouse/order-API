package com.phincon.order.api.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersDtoTest {

    @Test
    void OrdersDtoConstructorTest() {
        OrdersDto orderDto = new OrdersDto("1", "1", 1000L, "1");
        assertEquals("1", orderDto.getId());
        assertEquals("1", orderDto.getProductId());
        assertEquals(1000L, orderDto.getPrice());
        assertEquals("1", orderDto.getActionId());
    }

    @Test
    void OrdersDtoTest(){
        OrdersDto orderDto = new OrdersDto();
        orderDto.setId("1");
        orderDto.setProductId("1");
        orderDto.setPrice(1000L);
        orderDto.setActionId("1");
        assertEquals("1", orderDto.getId());
        assertEquals("1", orderDto.getProductId());
        assertEquals(1000L, orderDto.getPrice());
        assertEquals("1", orderDto.getActionId());
    }
}