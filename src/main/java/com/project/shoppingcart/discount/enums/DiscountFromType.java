package com.project.shoppingcart.discount.enums;

import com.project.shoppingcart.discount.Amount;
import com.project.shoppingcart.discount.Rate;

public enum DiscountFromType {
    RATE(Rate.class.getName(), Rate.class),
    AMOUNT(Amount.class.getName(), Amount.class);

    private final String discountFromClassName;
    private final Class<?> classType;

    DiscountFromType(String discountFromClassName, Class<?> classType) {
        this.classType = classType;
        this.discountFromClassName = discountFromClassName;
    }

    public String getDiscountFromClassName() {
        return discountFromClassName;
    }

    public Class<?> getClassType() {
        return classType;
    }
}
