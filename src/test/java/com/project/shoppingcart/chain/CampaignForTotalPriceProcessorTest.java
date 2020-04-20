package com.project.shoppingcart.chain;

import com.project.shoppingcart.discount.DiscountCalculator;
import com.project.shoppingcart.discount.model.DiscountModel;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({DiscountModel.class, DiscountCalculator.class})
public class CampaignForTotalPriceProcessorTest {

    @InjectMocks
    private CampaignForTotalPriceProcessor campaignForTotalPriceProcessor;

    @Mock
    private TotalPriceCalculationChain totalPriceCalculationChain;

    @Test
    public void shouldSetNext() {
        CouponForTotalPriceProcessor couponForTotalPriceProcessor = mock(CouponForTotalPriceProcessor.class);

        campaignForTotalPriceProcessor.setNext(couponForTotalPriceProcessor);

        assertTrue(couponForTotalPriceProcessor instanceof TotalPriceCalculationChain);
    }

    @Test
    public void shouldProcess() {
        ChainProcessorModel chainProcessorModel = new ChainProcessorModel();
        CartItem cartItem = CartItem.builder().quantity(2).totalPrice(BigDecimal.valueOf(100)).build();
        Campaign campaign = Campaign.builder().build();
        List<Campaign> campaigns = Arrays.asList(campaign, campaign);

        chainProcessorModel.setCartItem(cartItem);
        chainProcessorModel.setCampaigns(campaigns);

        mockStatic(DiscountModel.class);
        mockStatic(DiscountCalculator.class);

        DiscountModel discountModel = new DiscountModel();
        List<DiscountModel> discountModels = new ArrayList<>();
        discountModels.add(discountModel);

        BigDecimal maxDiscount = BigDecimal.valueOf(10);
        BigDecimal finalPrice = cartItem.getTotalPrice().subtract(maxDiscount);

        when(DiscountModel.toListFromCampaigns(campaigns, cartItem.getTotalPrice(), cartItem.getQuantity())).thenReturn(discountModels);
        when(DiscountCalculator.findMaxDiscount(discountModels)).thenReturn(maxDiscount);

        campaignForTotalPriceProcessor.process(chainProcessorModel);

        assertEquals(cartItem.getCampaignDiscount(), maxDiscount);
        assertEquals(cartItem.getFinalPrice(), finalPrice);

        verify(totalPriceCalculationChain).process(chainProcessorModel);
    }
}