package com.project.shoppingcart.converter;

import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.model.CartItemModel;
import org.springframework.stereotype.Service;

@Service
public class CartItemConverter extends BaseConverter<CartItem, CartItemModel> {

    @Override
    public CartItem toEntity(CartItemModel cartItemModel) {
        return CartItem.builder()
                .campaignDiscount(cartItemModel.getTotalDiscount())
                .totalPrice(cartItemModel.getTotalPrice())
                .unitPrice(cartItemModel.getUnitPrice())
                .build();
    }

    @Override
    public CartItemModel toModel(CartItem cartItem) {
        return CartItemModel.builder()
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getTotalPrice())
                .unitPrice(cartItem.getUnitPrice())
                .categoryName(cartItem.getProduct().getCategory().getTitle())
                .productName(cartItem.getProduct().getTitle())
                .totalDiscount(cartItem.getCampaignDiscount()).build();
    }
}
