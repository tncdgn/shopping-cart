package com.project.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemModel extends BaseModel {

    private int quantity;
    private String productName;
    private String categoryName;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
}
