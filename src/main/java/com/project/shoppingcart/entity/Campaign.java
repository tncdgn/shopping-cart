package com.project.shoppingcart.entity;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CAMPAIGN")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Campaign extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "MIN_PURCHASE_QUANTITY")
    private BigDecimal minPurchaseLimit;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getMinPurchaseLimit() {
        return minPurchaseLimit;
    }

    public void setMinPurchaseLimit(BigDecimal minPurchaseQuantity) {
        this.minPurchaseLimit = minPurchaseQuantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public DiscountFromType getDiscountFromType() {
        return discountFromType;
    }

    public void setDiscountFromType(DiscountFromType discountFromType) {
        this.discountFromType = discountFromType;
    }
}
