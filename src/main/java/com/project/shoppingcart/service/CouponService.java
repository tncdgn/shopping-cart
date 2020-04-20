package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.CouponDao;
import com.project.shoppingcart.entity.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService extends BaseService<Coupon> {

    @Autowired
    private CouponDao couponDao;

    @Override
    public void save(Coupon coupon) {
        couponDao.save(coupon);
    }

    @Override
    public Optional<Coupon> findById(long id) {
        return couponDao.findById(id);
    }

    public List<Coupon> findAll() {
        return couponDao.findAll();
    }
}
