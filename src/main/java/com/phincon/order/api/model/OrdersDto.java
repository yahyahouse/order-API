package com.phincon.order.api.model;

import lombok.Data;

@Data
public class OrdersDto {
    String id;
    String productId;
    Long price;
    String actionId;
}
