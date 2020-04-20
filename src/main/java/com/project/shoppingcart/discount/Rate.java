package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountFromModel;

import java.math.BigDecimal;

public class Rate implements DiscountFrom {

    @Override
    public BigDecimal calculate(DiscountFromModel discountFromModel) {
        return discountFromModel.getTotalPrice().multiply(discountFromModel.getDiscount()).divide(BigDecimal.valueOf(100));
    }
}
