package com.project.shoppingcart.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCartModel extends BaseModel {

    public List<CartItemModel> cartItemModels = new ArrayList<>();

    private BigDecimal couponDiscount;
    private BigDecimal deliveryCost;
    private BigDecimal totalPrice;

}
