package com.phincon.order.api.model;

import lombok.Data;

@Data
public class Orders {
    String id;
    String productId;
    String status;
    Long price;
    String actionId;
}
