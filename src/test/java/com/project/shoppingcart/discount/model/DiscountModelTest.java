package com.project.shoppingcart.discount.model;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import com.project.shoppingcart.discount.enums.DiscountType;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.Coupon;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DiscountModelTest {

    @Test
    public void shouldCreateListFromCoupons() {
        Coupon coupon = Coupon.builder()
                .discount(BigDecimal.valueOf(20))
                .discountFromType(DiscountFromType.AMOUNT)
                .minPurchaseAmount(BigDecimal.valueOf(2))
                .build();


        List<DiscountModel> discountModels = DiscountModel.toListFromCoupons(Arrays.asList(coupon), BigDecimal.valueOf(70));

        assertEquals(discountModels.size(), 1);

        DiscountModel discountModel = discountModels.get(0);

        assertEquals(discountModel.getTotalPrice(), BigDecimal.valueOf(70));
        assertEquals(discountModel.getDiscount(), coupon.getDiscount());
        assertEquals(discountModel.getDiscountFromType(), coupon.getDiscountFromType());
        assertEquals(discountModel.getDiscountType(), DiscountType.COUPON);
        assertEquals(discountModel.getMinPurchaseLimit(), coupon.getMinPurchaseAmount());
    }

    @Test
    public void shouldCreateListFromCampaign() {
        Campaign campaign = Campaign.builder()
                .discount(BigDecimal.valueOf(20))
                .discountFromType(DiscountFromType.AMOUNT)
                .minPurchaseLimit(BigDecimal.valueOf(2))
                .build();


        List<DiscountModel> discountModels = DiscountModel.toListFromCampaigns(Arrays.asList(campaign), BigDecimal.valueOf(70), 3);

        assertEquals(discountModels.size(), 1);

        DiscountModel discountModel = discountModels.get(0);

        assertEquals(discountModel.getTotalPrice(), BigDecimal.valueOf(70));
        assertEquals(discountModel.getDiscount(), campaign.getDiscount());
        assertEquals(discountModel.getDiscountFromType(), campaign.getDiscountFromType());
        assertEquals(discountModel.getDiscountType(), DiscountType.CAMPAIGN);
        assertEquals(discountModel.getMinPurchaseLimit(), campaign.getMinPurchaseLimit());
        assertEquals(discountModel.getQuantity(), BigDecimal.valueOf(3));
    }
}