package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountFromModel;

import java.math.BigDecimal;

public class Amount implements DiscountFrom {

    @Override
    public BigDecimal calculate(DiscountFromModel discountFromModel) {
        return discountFromModel.getDiscount();
    }
}
