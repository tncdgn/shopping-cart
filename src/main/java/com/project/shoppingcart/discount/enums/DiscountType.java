package com.project.shoppingcart.discount.enums;

import com.project.shoppingcart.discount.CampaignDiscount;
import com.project.shoppingcart.discount.CouponDiscount;

public enum DiscountType {
    CAMPAIGN(CampaignDiscount.class.getName(), CampaignDiscount.class),
    COUPON(CouponDiscount.class.getName(), CouponDiscount.class);

    private final String discountClassName;
    private final Class<?> classType;

    DiscountType(String discountClassName, Class<?> classType) {
        this.classType = classType;
        this.discountClassName = discountClassName;
    }

    public String getDiscountClassName() {
        return discountClassName;
    }

    public Class<?> getClassType() {
        return classType;
    }
}
