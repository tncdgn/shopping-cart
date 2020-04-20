package com.project.shoppingcart.chain;

import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.entity.Coupon;
import com.project.shoppingcart.entity.ShoppingCart;
import lombok.Data;

import java.util.List;

@Data
public class ChainProcessorModel {

    private List<Campaign> campaigns;
    private List<Coupon> coupons;
    private ShoppingCart shoppingCart;
    private CartItem cartItem;
}
