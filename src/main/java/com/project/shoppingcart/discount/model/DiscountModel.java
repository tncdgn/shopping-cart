package com.project.shoppingcart.discount.model;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import com.project.shoppingcart.discount.enums.DiscountType;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.Coupon;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiscountModel {
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private DiscountType discountType;
    private DiscountFromType discountFromType;
    private BigDecimal discount;
    private BigDecimal minPurchaseLimit;

    public static List<DiscountModel> toListFromCoupons(List<Coupon> coupons, BigDecimal totalPrice) {
        List<DiscountModel> discountModels = new ArrayList<>();

        for (Coupon coupon : coupons) {
            DiscountModel discountModel = new DiscountModel();
            discountModel.setTotalPrice(totalPrice);
            discountModel.setDiscount(coupon.getDiscount());
            discountModel.setDiscountFromType(coupon.getDiscountFromType());
            discountModel.setDiscountType(DiscountType.COUPON);
            discountModel.setMinPurchaseLimit(coupon.getMinPurchaseAmount());
            discountModels.add(discountModel);
        }

        return discountModels;
    }

    public static List<DiscountModel> toListFromCampaigns(List<Campaign> campaigns, BigDecimal totalPrice, int quantity) {
        List<DiscountModel> discountModels = new ArrayList<>();

        for (Campaign campaign : campaigns) {
            DiscountModel discountModel = new DiscountModel();
            discountModel.setTotalPrice(totalPrice);
            discountModel.setDiscount(campaign.getDiscount());
            discountModel.setDiscountFromType(campaign.getDiscountFromType());
            discountModel.setDiscountType(DiscountType.CAMPAIGN);
            discountModel.setMinPurchaseLimit(campaign.getMinPurchaseLimit());
            discountModel.setQuantity(BigDecimal.valueOf(quantity));

            discountModels.add(discountModel);
        }

        return discountModels;
    }
}
