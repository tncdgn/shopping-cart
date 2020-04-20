package com.project.shoppingcart.entity;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    @Test
    public void shouldUpdateFinalPrice() {
        CartItem cartItem = CartItem.builder().finalPrice(BigDecimal.valueOf(20)).build();

        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .cartItems(cartItems).build();

        shoppingCart.updateFinalPrice();

        assertEquals(shoppingCart.getTotalPrice(), BigDecimal.valueOf(20));
    }
}