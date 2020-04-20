package com.project.shoppingcart.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartItemTest {

    @Test
    public void shouldPrepareTotalPrice() {
        CartItem cartItem = CartItem.builder()
                .unitPrice(BigDecimal.valueOf(20))
                .quantity(3).build();

        cartItem.prepareTotalPrice();

        assertEquals(cartItem.getTotalPrice(), BigDecimal.valueOf(60));
    }
}