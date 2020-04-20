package com.project.shoppingcart.service;

import com.project.shoppingcart.model.DeliveryCostModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DeliveryCostService {

    public BigDecimal calculateDeliveryCost(DeliveryCostModel deliveryCostModel) {
        BigDecimal deliveryCost = deliveryCostModel.getCostPerDelivery()
                .multiply(BigDecimal.valueOf(deliveryCostModel.getNumberOfDeliveries()))
                .add(deliveryCostModel.getCostPerProduct().multiply(BigDecimal.valueOf(deliveryCostModel.getNumberOfProducts())))
                .add(deliveryCostModel.getFixCost());

        return deliveryCost;
    }
}
