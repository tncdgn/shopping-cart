package com.project.shoppingcart.response;

import com.project.shoppingcart.model.ShoppingCartModel;
import lombok.Data;

@Data
public class ShoppingCartResponse extends BaseResponse {
    private ShoppingCartModel shoppingCartModel;
}
