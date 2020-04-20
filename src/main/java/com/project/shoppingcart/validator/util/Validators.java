package com.project.shoppingcart.validator.util;

import com.project.shoppingcart.validator.util.matchers.IsNotBlankString;
import org.hamcrest.Matcher;
import org.hamcrest.number.OrderingComparison;

public final class Validators {

    private Validators() {
    }

    public static Matcher<?> notBlank() {
        return new IsNotBlankString();
    }


    public static <T extends Comparable<T>> Matcher<?> greaterOrEqualTo(T e) {
        return OrderingComparison.greaterThanOrEqualTo(e);
    }
}
