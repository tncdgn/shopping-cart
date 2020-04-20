package com.project.shoppingcart.chain;

import com.project.shoppingcart.discount.DiscountCalculator;
import com.project.shoppingcart.discount.model.DiscountModel;
import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.entity.Coupon;
import com.project.shoppingcart.entity.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DiscountModel.class, DiscountCalculator.class})
public class CouponForTotalPriceProcessorTest {

    @InjectMocks
    private CouponForTotalPriceProcessor couponForTotalPriceProcessor;

    @Mock
    private TotalPriceCalculationChain totalPriceCalculationChain;

    @Test
    public void shouldSetNext() {
        DeliveryCostForTotalPriceProcessor deliveryCostForTotalPriceProcessor = mock(DeliveryCostForTotalPriceProcessor.class);

        couponForTotalPriceProcessor.setNext(couponForTotalPriceProcessor);

        assertTrue(couponForTotalPriceProcessor instanceof TotalPriceCalculationChain);
    }

    @Test
    public void shouldProcess() {
        ChainProcessorModel chainProcessorModel = new ChainProcessorModel();

        CartItem cartItem = CartItem.builder().quantity(2).totalPrice(BigDecimal.valueOf(100)).finalPrice(BigDecimal.valueOf(100)).build();
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);

        ShoppingCart shoppingCart = ShoppingCart.builder().cartItems(cartItems).totalPrice(BigDecimal.valueOf(100)).build();

        Coupon coupon = Coupon.builder().build();
        chainProcessorModel.setCoupons(Arrays.asList(coupon));
        chainProcessorModel.setShoppingCart(shoppingCart);

        mockStatic(DiscountModel.class);
        mockStatic(DiscountCalculator.class);

        DiscountModel discountModel = new DiscountModel();
        List<DiscountModel> discountModels = new ArrayList<>();
        discountModels.add(discountModel);

        BigDecimal maxDiscount = BigDecimal.valueOf(10);
        BigDecimal finalPrice = shoppingCart.getTotalPrice().subtract(maxDiscount);

        when(DiscountModel.toListFromCoupons(Arrays.asList(coupon), shoppingCart.getTotalPrice())).thenReturn(discountModels);
        when(DiscountCalculator.findMaxDiscount(discountModels)).thenReturn(maxDiscount);

        couponForTotalPriceProcessor.process(chainProcessorModel);

        assertEquals(shoppingCart.getCouponDiscount(), maxDiscount);
        assertEquals(shoppingCart.getTotalPriceAfterDiscount(), finalPrice);

        verify(totalPriceCalculationChain).process(chainProcessorModel);
    }
}