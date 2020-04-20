package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountFromModel;
import com.project.shoppingcart.discount.model.DiscountModel;

import java.math.BigDecimal;

public abstract class Discount {

    protected DiscountFrom discountFrom;

    public Discount(DiscountFrom discountFrom) {
        this.discountFrom = discountFrom;
    }

    abstract BigDecimal calculate(DiscountModel discountModel);

    protected DiscountFromModel prepareModel(BigDecimal totalPrice, BigDecimal discount) {
        return DiscountFromModel.builder().discount(discount).totalPrice(totalPrice).build();
    }
}
