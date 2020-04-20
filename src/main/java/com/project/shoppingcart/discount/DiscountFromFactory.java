package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.enums.DiscountFromType;

public class DiscountFromFactory {

    public static DiscountFrom getDiscountType(DiscountFromType discountFromType) {

        try {
            return (DiscountFrom) Class.forName(discountFromType.getDiscountFromClassName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
