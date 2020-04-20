package com.project.shoppingcart.service;

import com.project.shoppingcart.model.DeliveryCostModel;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DeliveryCostServiceTest {

    @Test
    public void shouldCalculateDeliveryCost() {
        DeliveryCostService deliveryCostService = new DeliveryCostService();

        DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2, 2);

        deliveryCostModel.setFixCost(BigDecimal.valueOf(2.99));
        deliveryCostModel.setNumberOfDeliveries(3);
        deliveryCostModel.setNumberOfProducts(3);

        BigDecimal discount = deliveryCostService.calculateDeliveryCost(deliveryCostModel);

        assertEquals(discount, BigDecimal.valueOf(32.99));
    }
}