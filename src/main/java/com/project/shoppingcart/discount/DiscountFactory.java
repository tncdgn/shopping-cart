package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.enums.DiscountType;


public class DiscountFactory {

    public static Discount getDiscountType(DiscountType discountType, DiscountFrom discountFrom) {

        try {
            return (Discount) Class.forName(discountType.getDiscountClassName()).getDeclaredConstructor(DiscountFrom.class).newInstance(discountFrom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
