package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountModel;

import java.math.BigDecimal;
import java.util.List;

public class DiscountCalculator {

    public static BigDecimal findMaxDiscount(List<DiscountModel> discountModels) {
        BigDecimal maxDiscount = BigDecimal.ZERO;

        for (DiscountModel discountModel : discountModels) {
            DiscountFrom discountFrom = DiscountFromFactory.getDiscountType(discountModel.getDiscountFromType());
            BigDecimal expectedDiscount = DiscountFactory.getDiscountType(discountModel.getDiscountType(), discountFrom).calculate(discountModel);

            if (expectedDiscount.compareTo(maxDiscount) > 0) {
                maxDiscount = expectedDiscount;
            }
        }

        return maxDiscount;
    }
}
