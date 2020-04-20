package com.project.shoppingcart.chain;

import com.project.shoppingcart.discount.DiscountCalculator;
import com.project.shoppingcart.discount.model.DiscountModel;
import com.project.shoppingcart.entity.Coupon;
import com.project.shoppingcart.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CouponForTotalPriceProcessor implements TotalPriceCalculationChain {

    private TotalPriceCalculationChain processor;

    @Override
    public void setNext(TotalPriceCalculationChain processor) {
        this.processor = processor;
    }

    @Override
    public void process(ChainProcessorModel chainProcessorModel) {
        ShoppingCart shoppingCart = chainProcessorModel.getShoppingCart();
        shoppingCart.updateFinalPrice();

        BigDecimal maxDiscount = findMaxDiscountPrice(chainProcessorModel.getCoupons(), shoppingCart.getTotalPrice());
        shoppingCart.setCouponDiscount(maxDiscount);
        shoppingCart.setTotalPriceAfterDiscount(shoppingCart.getTotalPrice().subtract(maxDiscount));

        processor.process(chainProcessorModel);
    }

    private BigDecimal findMaxDiscountPrice(List<Coupon> coupons, BigDecimal totalPrice) {
        List<DiscountModel> discountModel = DiscountModel.toListFromCoupons(coupons, totalPrice);

        return DiscountCalculator.findMaxDiscount(discountModel);
    }
}
