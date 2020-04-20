package com.project.shoppingcart.service;

import com.project.shoppingcart.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class DataLoaderServiceTest {

    @InjectMocks
    private DataLoaderService dataLoaderService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @Mock
    private CampaignService campaignService;

    @Mock
    private CouponService couponService;

    @Test
    public void shouldPrepareData() {
        dataLoaderService.init();

        verify(categoryService).save(any(Category.class));
        verify(categoryService).saveBulk(anyList());

        verify(productService, times(5)).saveBulk(anyList());

        verify(campaignService).saveBulk(anyList());
        verify(couponService).saveBulk(anyList());
    }
}