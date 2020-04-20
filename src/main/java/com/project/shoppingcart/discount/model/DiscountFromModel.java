package com.project.shoppingcart.discount.model;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountFromModel {
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private DiscountFromType discountFromType;

    public DiscountFromModel(BigDecimal discount, BigDecimal totalPrice) {
        this.discount = discount;
        this.totalPrice = totalPrice;
    }
}
