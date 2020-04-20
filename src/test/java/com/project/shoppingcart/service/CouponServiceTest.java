package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.CouponDao;
import com.project.shoppingcart.entity.Coupon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class CouponServiceTest {

    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponDao couponDao;

    @Test
    public void shouldSave() {
        Coupon coupon = new Coupon();

        couponService.save(coupon);

        verify(couponDao).save(coupon);
    }

    @Test
    public void shouldFindById() {
        couponService.findById(1l);

        verify(couponDao).findById(1l);
    }

    @Test
    public void shouldFindAll() {
        couponService.findAll();

        verify(couponDao).findAll();
    }
}