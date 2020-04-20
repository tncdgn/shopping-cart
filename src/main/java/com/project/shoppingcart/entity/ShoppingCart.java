package com.project.shoppingcart.entity;

import com.project.shoppingcart.constant.ShoppingCartConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SHOPPING_CART")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(name = "BUYER_ID")
    private Long buyerId = ShoppingCartConstant.CURRENT_BUYER_ID;

    @Column(name = "TOTAL_AMOUNT_AFTER_DISCOUNT")
    private BigDecimal totalPriceAfterDiscount = BigDecimal.ZERO;

    @Column(name = "COUPON_DISCOUNT")
    private BigDecimal couponDiscount = BigDecimal.ZERO;

    @Column(name = "DELIVERY_COST")
    private BigDecimal deliveryCost = BigDecimal.ZERO;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Override
    public Long getId() {
        return id;
    }


    public void updateFinalPrice() {
        BigDecimal finalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : getCartItems()) {
            finalPrice = finalPrice.add(cartItem.getFinalPrice());
        }
        setTotalPrice(finalPrice);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public BigDecimal getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }

    public void setTotalPriceAfterDiscount(BigDecimal totalPriceAfterDiscount) {
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
    }

    public BigDecimal getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(BigDecimal couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
