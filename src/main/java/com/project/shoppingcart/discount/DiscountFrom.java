package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountFromModel;

import java.math.BigDecimal;

public interface DiscountFrom {

    BigDecimal calculate(DiscountFromModel discountFromModel);
}
