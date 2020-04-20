package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.CartItemDao;
import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.entity.ShoppingCart;
import com.project.shoppingcart.model.AddCartItemModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class CartItemServiceTest {

    @InjectMocks
    private CartItemService cartItemService;

    @Mock
    private CartItemDao cartItemDao;

    @Mock
    private ProductService productService;

    @Test
    public void shouldSave() {
        CartItem cartItem = new CartItem();

        cartItemService.save(cartItem);

        verify(cartItemDao).save(cartItem);
    }


    @Test
    public void shouldFindById() {
        cartItemService.findById(1l);

        verify(cartItemDao).findById(1l);
    }

    @Test
    public void shouldPrepareCartItem() {
        AddCartItemModel cartItemModel = new AddCartItemModel();
        cartItemModel.setProductId(123l);
        cartItemModel.setQuantity(2);

        ShoppingCart shoppingCart = new ShoppingCart();

        Product product = Product.builder().price(BigDecimal.valueOf(15)).build();
        Optional<Product> optionalProduct = Optional.ofNullable(product);

        when(productService.findById(123l)).thenReturn(optionalProduct);
        CartItem cartItem = cartItemService.prepareCartItem(cartItemModel, shoppingCart);

        assertEquals(cartItem.getProduct(), product);
        assertEquals(cartItem.getQuantity(), cartItemModel.getQuantity());
        assertEquals(cartItem.getUnitPrice(), product.getPrice());
        assertEquals(cartItem.getShoppingCart(), shoppingCart);
        assertEquals(cartItem.getTotalPrice(), BigDecimal.valueOf(30));
    }
}