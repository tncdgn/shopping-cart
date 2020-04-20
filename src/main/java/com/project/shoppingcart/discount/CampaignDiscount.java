package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountModel;

import java.math.BigDecimal;

public class CampaignDiscount extends Discount {

    public CampaignDiscount(DiscountFrom discountFrom) {
        super(discountFrom);
    }

    @Override
    BigDecimal calculate(DiscountModel discountModel) {
        BigDecimal discount = BigDecimal.ZERO;

        if (discountModel.getQuantity().compareTo(discountModel.getMinPurchaseLimit()) > 0) {
            discount = discountFrom.calculate(prepareModel(discountModel.getTotalPrice(), discountModel.getDiscount()));
        }

        return discount;
    }
}
