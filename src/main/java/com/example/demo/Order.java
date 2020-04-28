package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;


@Entity
@Table(name="ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue
    Long id;
    int qty;
    String productName;
    Long productId;

    @PostPersist
    public void eventPublish(){
        OrderPlaced orderPlaced = new OrderPlaced();
        orderPlaced.setOrderId(this.getId());
        orderPlaced.setQty(this.getQty());
        orderPlaced.setProductId(this.getProductId());
        orderPlaced.setProductName(this.getProductName());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(orderPlaced);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }
        System.out.println(json);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setId(int qty) {
        this.qty = qty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void getProductId(Long productId) {
        this.productId = productId;
    }
}
