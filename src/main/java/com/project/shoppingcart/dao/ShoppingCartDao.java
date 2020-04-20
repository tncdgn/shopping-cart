package com.project.shoppingcart.dao;

import com.project.shoppingcart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart getByBuyerId(long buyerId);
}
