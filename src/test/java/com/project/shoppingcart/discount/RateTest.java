package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountFromModel;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class RateTest {

    @Test
    public void shouldCalculateDiscount() {
        Rate rate = new Rate();

        DiscountFromModel discountModel = new DiscountFromModel();
        discountModel.setDiscount(BigDecimal.valueOf(10));
        discountModel.setTotalPrice(BigDecimal.valueOf(100));

        BigDecimal discount = rate.calculate(discountModel);
        assertEquals(discount, BigDecimal.valueOf(10));
    }
}