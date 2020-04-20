package com.project.shoppingcart.chain;

import com.project.shoppingcart.entity.ShoppingCart;
import com.project.shoppingcart.model.DeliveryCostModel;
import com.project.shoppingcart.service.DeliveryCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class DeliveryCostForTotalPriceProcessor implements TotalPriceCalculationChain {

    private TotalPriceCalculationChain processor;

    @Autowired
    private DeliveryCostService deliveryCostService;

    @Override
    public void setNext(TotalPriceCalculationChain processor) {
        this.processor = processor;
    }

    @Override
    public void process(ChainProcessorModel chainProcessorModel) {
        ShoppingCart shoppingCart = chainProcessorModel.getShoppingCart();

        DeliveryCostModel deliveryCostModel = new DeliveryCostModel(getNumberOfDeliveries(shoppingCart), getNumberOfProducts(shoppingCart));

        BigDecimal deliveryCost = deliveryCostService.calculateDeliveryCost(deliveryCostModel);

        shoppingCart.setDeliveryCost(deliveryCost);
        shoppingCart.setTotalPrice(shoppingCart.getTotalPriceAfterDiscount().add(deliveryCost));
    }

    private int getNumberOfDeliveries(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream().map(cartItem -> cartItem.getProduct().getCategory()).collect(Collectors.toSet()).size();
    }

    private int getNumberOfProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().size();
    }
}
