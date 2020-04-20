package com.project.shoppingcart.service;

import com.project.shoppingcart.chain.TotalPriceCalculationChainStarter;
import com.project.shoppingcart.converter.CartItemConverter;
import com.project.shoppingcart.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity> {

    @Autowired
    protected ProductService productService;

    @Autowired
    protected CampaignService campaignService;

    @Autowired
    protected CartItemService cartItemService;

    @Autowired
    protected CouponService couponService;

    @Autowired
    protected DeliveryCostService deliveryCostService;

    @Autowired
    protected CartItemConverter cartItemConverter;

    @Autowired
    protected TotalPriceCalculationChainStarter chainStarter;

    public abstract void save(T t);

    public abstract Optional<T> findById(long id);

    public void saveBulk(List<T> tList) {
        tList.forEach(t -> {
            save(t);
        });
    }
}
