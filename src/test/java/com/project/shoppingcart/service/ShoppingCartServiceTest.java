package com.project.shoppingcart.service;

import com.project.shoppingcart.constant.ShoppingCartConstant;
import com.project.shoppingcart.chain.ChainProcessorModel;
import com.project.shoppingcart.chain.TotalPriceCalculationChainStarter;
import com.project.shoppingcart.converter.CartItemConverter;
import com.project.shoppingcart.dao.ShoppingCartDao;
import com.project.shoppingcart.entity.*;
import com.project.shoppingcart.exception.EmptyCartException;
import com.project.shoppingcart.model.AddCartItemModel;
import com.project.shoppingcart.model.CartItemModel;
import com.project.shoppingcart.model.ShoppingCartModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Mock
    private ShoppingCartDao shoppingCartDao;

    @Mock
    private CartItemService cartItemService;

    @Mock
    private CampaignService campaignService;

    @Mock
    private CouponService couponService;

    @Mock
    private TotalPriceCalculationChainStarter chainStarter;

    @Mock
    private CartItemConverter cartItemConverter;

    @Test
    public void shouldSave() {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCartService.save(shoppingCart);

        verify(shoppingCartDao).save(shoppingCart);
    }

    @Test
    public void shouldFindById() {
        shoppingCartService.findById(1l);

        verify(shoppingCartDao).findById(1l);
    }

    @Test
    public void shouldAddItem() {
        when(shoppingCartDao.getByBuyerId(ShoppingCartConstant.CURRENT_BUYER_ID)).thenReturn(null);

        ShoppingCart shoppingCart = new ShoppingCart();

        when(shoppingCartDao.save(any())).thenReturn(shoppingCart);

        AddCartItemModel cartItemModel = new AddCartItemModel();

        Category category = Category.builder().build();
        Product product = Product.builder().category(category).build();

        CartItem cartItem = CartItem.builder().product(product).build();

        shoppingCart.getCartItems().add(cartItem);

        Campaign campaign = Campaign.builder().build();
        List<Campaign> campaigns = Arrays.asList(campaign);

        Coupon coupon = Coupon.builder().build();
        List<Coupon> coupons = Arrays.asList(coupon);

        when(cartItemService.prepareCartItem(cartItemModel, shoppingCart)).thenReturn(cartItem);
        when(campaignService.findByCategory(category)).thenReturn(campaigns);
        when(couponService.findAll()).thenReturn(coupons);

        ChainProcessorModel chainProcessorModel = new ChainProcessorModel();
        chainProcessorModel.setCampaigns(campaigns);
        chainProcessorModel.setCoupons(coupons);
        chainProcessorModel.setCartItem(cartItem);
        chainProcessorModel.setShoppingCart(shoppingCart);

        shoppingCartService.addItem(cartItemModel);

        verify(chainStarter).startChain(chainProcessorModel);
        verify(shoppingCartDao, times(2)).save(shoppingCart);
    }

    @Test
    public void shouldGetCurrentCart() {
        shoppingCartService.getCurrentCart();

        verify(shoppingCartDao).getByBuyerId(ShoppingCartConstant.CURRENT_BUYER_ID);
    }

    @Test
    public void shouldCreateNewCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCartService.createNewCart();

        verify(shoppingCartDao).save(shoppingCart);
    }

    @Test(expected = EmptyCartException.class)
    public void shouldNotPrintCartHasNotCreatedYet() {

        when(shoppingCartDao.getByBuyerId(ShoppingCartConstant.CURRENT_BUYER_ID)).thenReturn(null);

        shoppingCartService.print();
    }

    @Test(expected = EmptyCartException.class)
    public void shouldNotPrintCartIfEmpty() {
        ShoppingCart shoppingCart = new ShoppingCart();

        when(shoppingCartDao.getByBuyerId(ShoppingCartConstant.CURRENT_BUYER_ID)).thenReturn(shoppingCart);

        shoppingCartService.print();
    }

    @Test
    public void shouldPrepareModelForPrintCart() {
        CartItem cartItem = CartItem.builder().quantity(2).unitPrice(BigDecimal.valueOf(15)).build();

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setCouponDiscount(BigDecimal.valueOf(10));
        shoppingCart.setDeliveryCost(BigDecimal.valueOf(23));
        shoppingCart.setTotalPrice(BigDecimal.valueOf(70));
        shoppingCart.getCartItems().add(cartItem);

        CartItemModel cartItemModel = CartItemModel.builder().quantity(2).totalPrice(BigDecimal.valueOf(20)).build();
        when(cartItemConverter.toModel(cartItem)).thenReturn(cartItemModel);

        when(shoppingCartDao.getByBuyerId(ShoppingCartConstant.CURRENT_BUYER_ID)).thenReturn(shoppingCart);

        ShoppingCartModel shoppingCartModel = shoppingCartService.print();

        assertEquals(shoppingCartModel.getCouponDiscount(), shoppingCart.getCouponDiscount());
        assertEquals(shoppingCartModel.getDeliveryCost(), shoppingCart.getDeliveryCost());
        assertEquals(shoppingCartModel.getTotalPrice(), shoppingCart.getTotalPrice());
        CartItemModel cartItemModel1 = shoppingCartModel.getCartItemModels().get(0);

        assertEquals(cartItemModel, cartItemModel1);
    }
}