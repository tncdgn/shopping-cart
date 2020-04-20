package com.project.shoppingcart.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeliveryCostModel {

    private BigDecimal costPerDelivery = BigDecimal.valueOf(5);
    private BigDecimal costPerProduct = BigDecimal.valueOf(5);
    private BigDecimal fixCost = BigDecimal.valueOf(2.99);
    private int numberOfDeliveries;
    private int numberOfProducts;

    public DeliveryCostModel(int numberOfDeliveries, int numberOfProducts) {
        this.numberOfDeliveries = numberOfDeliveries;
        this.numberOfProducts = numberOfProducts;
    }
}
