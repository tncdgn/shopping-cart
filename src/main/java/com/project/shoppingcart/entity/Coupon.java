package com.project.shoppingcart.entity;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "COUPON")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MIN_PURCHASE_AMOUNT")
    private BigDecimal minPurchaseAmount;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;

    @Column(length = 30, name = "DISCOUNT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private DiscountFromType discountFromType;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public DiscountFromType getDiscountFromType() {
        return discountFromType;
    }

    public void setDiscountFromType(DiscountFromType discountFromType) {
        this.discountFromType = discountFromType;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }


}
