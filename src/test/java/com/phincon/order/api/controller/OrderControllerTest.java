package com.phincon.order.api.controller;

import com.phincon.order.api.model.Orders;
import com.phincon.order.api.model.OrdersDto;
import com.phincon.order.api.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
public class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCreateOrder() throws InterruptedException {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId("1");
        ordersDto.setProductId("1");
        ordersDto.setPrice(1L);
        ordersDto.setActionId("1");
        Orders orders = new Orders("1", "1", "Complete", 1L, "1");
        Mockito.when(orderService.createOrder(ordersDto)).thenReturn(Mono.just(orders));
        webTestClient.post().uri("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(ordersDto), OrdersDto.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void testGetOrder() {
        Orders orders = new Orders("1", "1", "Complete", 1L, "1");
        Mockito.when(orderService.getOrderById("1")).thenReturn(Mono.just(orders));
        webTestClient.get().uri("/order/get-orders/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Orders.class);
    }
}
