package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import com.project.shoppingcart.discount.enums.DiscountType;
import com.project.shoppingcart.discount.model.DiscountModel;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class DiscountCalculatorTest {

    @Test
    public void shouldReturnZeroIfNoDiscount() {
        assertEquals(DiscountCalculator.findMaxDiscount(Collections.EMPTY_LIST), BigDecimal.ZERO);
    }

    @Test
    public void shouldFindMaxDiscount() {
        DiscountModel discountModel = new DiscountModel();
        discountModel.setDiscountFromType(DiscountFromType.AMOUNT);
        discountModel.setDiscountType(DiscountType.CAMPAIGN);
        discountModel.setQuantity(BigDecimal.valueOf(5));
        discountModel.setMinPurchaseLimit(BigDecimal.valueOf(3));
        discountModel.setTotalPrice(BigDecimal.valueOf(100));
        discountModel.setDiscount(BigDecimal.valueOf(20));

        BigDecimal discount = DiscountCalculator.findMaxDiscount(Arrays.asList(discountModel));

        assertEquals(discount, BigDecimal.valueOf(20));
    }
}