package com.project.shoppingcart.chain;

import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.entity.Category;
import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.entity.ShoppingCart;
import com.project.shoppingcart.model.DeliveryCostModel;
import com.project.shoppingcart.service.DeliveryCostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;


@RunWith(PowerMockRunner.class)
public class DeliveryCostForTotalPriceProcessorTest {

    @InjectMocks
    private DeliveryCostForTotalPriceProcessor deliveryCostForTotalPriceProcessor;

    @Mock
    private DeliveryCostService deliveryCostService;

    @Mock
    private TotalPriceCalculationChain totalPriceCalculationChain;

    @Test
    public void shouldSetNext() {
        CouponForTotalPriceProcessor couponForTotalPriceProcessor = mock(CouponForTotalPriceProcessor.class);

        deliveryCostForTotalPriceProcessor.setNext(couponForTotalPriceProcessor);

        assertTrue(couponForTotalPriceProcessor instanceof TotalPriceCalculationChain);
    }

    @Test
    public void shouldProcess() {
        ChainProcessorModel chainProcessorModel = new ChainProcessorModel();

        Category category = Category.builder().id(1l).build();
        Product product = Product.builder().category(category).build();

        CartItem cartItem = CartItem.builder().product(product).quantity(2).totalPrice(BigDecimal.valueOf(100)).finalPrice(BigDecimal.valueOf(100)).build();
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);

        BigDecimal totalPriceAfterDiscount = BigDecimal.valueOf(70);

        ShoppingCart shoppingCart = ShoppingCart.builder().totalPriceAfterDiscount(totalPriceAfterDiscount).cartItems(cartItems).totalPrice(BigDecimal.valueOf(100)).build();

        chainProcessorModel.setShoppingCart(shoppingCart);

        DeliveryCostModel deliveryCostModel = new DeliveryCostModel(1, 1);
        BigDecimal deliveryCost = BigDecimal.valueOf(19);
        BigDecimal totalPrice = totalPriceAfterDiscount.add(deliveryCost);

        when(deliveryCostService.calculateDeliveryCost(deliveryCostModel)).thenReturn(deliveryCost);

        deliveryCostForTotalPriceProcessor.process(chainProcessorModel);

        assertEquals(shoppingCart.getDeliveryCost(), deliveryCost);
        assertEquals(shoppingCart.getTotalPrice(), totalPrice);
    }
}