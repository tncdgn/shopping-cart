package com.project.shoppingcart.converter;

import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.entity.Category;
import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.model.CartItemModel;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CartItemConverterTest {

    @Test
    public void shouldConvertToEntity() {

        CartItemConverter cartItemConverter = new CartItemConverter();
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setTotalDiscount(BigDecimal.valueOf(10));
        cartItemModel.setTotalPrice(BigDecimal.valueOf(30));
        cartItemModel.setUnitPrice(BigDecimal.valueOf(10));

        CartItem cartItem = cartItemConverter.toEntity(cartItemModel);

        assertEquals(cartItem.getCampaignDiscount(), cartItemModel.getTotalDiscount());
        assertEquals(cartItem.getTotalPrice(), cartItemModel.getTotalPrice());
        assertEquals(cartItem.getUnitPrice(), cartItemModel.getUnitPrice());
    }

    @Test
    public void shouldConvertToModel() {
        CartItemConverter cartItemConverter = new CartItemConverter();
        Category category = Category.builder().title("category").build();
        Product product = Product.builder().title("product").category(category).build();

        CartItem cartItem = CartItem.builder()
                .quantity(10)
                .totalPrice(BigDecimal.valueOf(100))
                .unitPrice(BigDecimal.valueOf(10))
                .product(product)
                .campaignDiscount(BigDecimal.valueOf(15)).build();

        CartItemModel cartItemModel = cartItemConverter.toModel(cartItem);

        assertEquals(cartItem.getCampaignDiscount(), cartItemModel.getTotalDiscount());
        assertEquals(cartItem.getTotalPrice(), cartItemModel.getTotalPrice());
        assertEquals(cartItem.getUnitPrice(), cartItemModel.getUnitPrice());
        assertEquals(cartItem.getProduct().getCategory().getTitle(), cartItemModel.getCategoryName());
        assertEquals(cartItem.getProduct().getTitle(), cartItemModel.getProductName());
    }
}