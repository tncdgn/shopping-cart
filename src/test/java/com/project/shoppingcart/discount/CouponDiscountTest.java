package com.project.shoppingcart.discount;

import com.project.shoppingcart.discount.model.DiscountFromModel;
import com.project.shoppingcart.discount.model.DiscountModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class CouponDiscountTest {

    @InjectMocks
    private CouponDiscount couponDiscount;

    @Mock
    private DiscountFrom discountFrom;

    @Test
    public void shouldReturnZeroIfQuantityIsUnderMinLimit() {
        DiscountModel discountModel = new DiscountModel();
        discountModel.setTotalPrice(BigDecimal.valueOf(100));
        discountModel.setMinPurchaseLimit(BigDecimal.valueOf(105));

        BigDecimal discount = couponDiscount.calculate(discountModel);

        assertEquals(discount, BigDecimal.ZERO);

        verifyZeroInteractions(discountFrom);
    }

    @Test
    public void shouldReturnDiscount() {
        DiscountModel discountModel = new DiscountModel();
        discountModel.setQuantity(BigDecimal.valueOf(3));
        discountModel.setMinPurchaseLimit(BigDecimal.valueOf(50));
        discountModel.setDiscount(BigDecimal.valueOf(10));
        discountModel.setTotalPrice(BigDecimal.valueOf(100));

        DiscountFromModel discountFromModel = DiscountFromModel.builder()
                .discount(discountModel.getDiscount())
                .totalPrice(discountModel.getTotalPrice()).build();

        BigDecimal discount = BigDecimal.valueOf(10);

        when(discountFrom.calculate(discountFromModel)).thenReturn(discount);
        BigDecimal expectedDiscount = couponDiscount.calculate(discountModel);

        assertEquals(discount, expectedDiscount);
    }
}