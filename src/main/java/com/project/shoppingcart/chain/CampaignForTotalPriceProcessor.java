package com.project.shoppingcart.chain;

import com.project.shoppingcart.discount.DiscountCalculator;
import com.project.shoppingcart.discount.model.DiscountModel;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.CartItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CampaignForTotalPriceProcessor implements TotalPriceCalculationChain {

    private TotalPriceCalculationChain processor;

    @Override
    public void setNext(TotalPriceCalculationChain processor) {
        this.processor = processor;
    }

    @Override
    public void process(ChainProcessorModel chainProcessorModel) {
        CartItem cartItem = chainProcessorModel.getCartItem();

        BigDecimal campaignDiscount = findMaxDiscountPrice(chainProcessorModel.getCampaigns(), cartItem.getTotalPrice(), cartItem.getQuantity());
        cartItem.setCampaignDiscount(campaignDiscount);
        cartItem.setFinalPrice(cartItem.getTotalPrice().subtract(campaignDiscount));

        processor.process(chainProcessorModel);
    }

    private BigDecimal findMaxDiscountPrice(List<Campaign> campaigns, BigDecimal totalPrice, int quantity) {
        List<DiscountModel> discountModels = DiscountModel.toListFromCampaigns(campaigns, totalPrice, quantity);

        return DiscountCalculator.findMaxDiscount(discountModels);
    }
}
