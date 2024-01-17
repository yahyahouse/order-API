package com.phincon.order.api.model;

public class Orders {
    String id;
    String productId;
    String status;
    Long price;
    String actionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public Orders() {
    }

    public Orders(String id, String productId, String status, Long price, String actionId) {
        this.id = id;
        this.productId = productId;
        this.status = status;
        this.price = price;
        this.actionId = actionId;
    }
}
