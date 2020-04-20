package com.project.shoppingcart.dao;

import com.project.shoppingcart.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponDao extends JpaRepository<Coupon, Long> {
}
