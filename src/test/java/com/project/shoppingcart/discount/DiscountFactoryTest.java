package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.enums.DiscountType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountFactoryTest {

    @Test
    public void shouldGetDiscount() {
        Amount amount = new Amount();

        Discount discount = DiscountFactory.getDiscountType(DiscountType.CAMPAIGN, amount);

        assertEquals(discount.getClass().getName(), CampaignDiscount.class.getName());
    }
}