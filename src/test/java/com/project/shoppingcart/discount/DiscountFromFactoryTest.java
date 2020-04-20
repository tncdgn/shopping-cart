package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountFromFactoryTest {

    @Test
    public void shouldGetDiscountFrom() {
        DiscountFrom discountFrom = DiscountFromFactory.getDiscountType(DiscountFromType.AMOUNT);

        assertEquals(discountFrom.getClass().getName(), Amount.class.getName());
    }
}