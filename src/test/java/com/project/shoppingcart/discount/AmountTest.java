package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountFromModel;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AmountTest {

    @Test
    public void shouldGetDiscount() {
        Amount amount = new Amount();
        DiscountFromModel discountModel = new DiscountFromModel();
        discountModel.setDiscount(BigDecimal.valueOf(10));

        assertEquals(amount.calculate(discountModel), discountModel.getDiscount());
    }
}