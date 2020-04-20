package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountModel;

import java.math.BigDecimal;

public class CouponDiscount extends Discount {

    public CouponDiscount(DiscountFrom discountFrom) {
        super(discountFrom);
    }

    @Override
    BigDecimal calculate(DiscountModel discountModel) {
        BigDecimal discount = BigDecimal.ZERO;

        BigDecimal totalPrice = discountModel.getTotalPrice();

        if (totalPrice.compareTo(discountModel.getMinPurchaseLimit()) > 0) {
            discount = discountFrom.calculate(prepareModel(totalPrice, discountModel.getDiscount()));
        }

        return discount;
    }
}
