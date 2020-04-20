package com.project.shoppingcart.service;

import com.project.shoppingcart.discount.enums.DiscountFromType;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.Category;
import com.project.shoppingcart.entity.Coupon;
import com.project.shoppingcart.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class DataLoaderService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CouponService couponService;

    @PostConstruct
    public void init() {
        prepareData();
    }

    private void prepareData() {
        Category electronicCategory = Category.builder().title("Electronic").build();
        categoryService.save(electronicCategory);

        Category computerCategory = Category.builder().title("Computer").parent(electronicCategory).build();
        Category carCategory = Category.builder().title("Car").build();
        Category phoneCategory = Category.builder().title("Phone").parent(electronicCategory).build();
        Category shoesCategory = Category.builder().title("Shoes").build();
        Category headPhoneCategory = Category.builder().title("HeadPhone").parent(electronicCategory).build();

        List<Category> categories = Arrays.asList(computerCategory, phoneCategory, carCategory, shoesCategory, headPhoneCategory);
        categoryService.saveBulk(categories);

        Product macBook = Product.builder().category(computerCategory).price(BigDecimal.valueOf(100)).title("MacBookPro").build();
        Product hp = Product.builder().category(computerCategory).price(BigDecimal.valueOf(50)).title("HP").build();
        Product dell = Product.builder().category(computerCategory).price(BigDecimal.valueOf(60)).title("DELL").build();
        Product asus = Product.builder().category(computerCategory).price(BigDecimal.valueOf(70)).title("ASUS").build();

        List<Product> computerProducts = Arrays.asList(macBook, hp, dell, asus);
        productService.saveBulk(computerProducts);

        Product mercedes = Product.builder().category(carCategory).price(BigDecimal.valueOf(5000)).title("Mercedes A 180").build();
        Product bmw = Product.builder().category(carCategory).price(BigDecimal.valueOf(6000)).title("BMW 3.20D").build();
        Product ferrari = Product.builder().category(carCategory).price(BigDecimal.valueOf(12000)).title("Ferrari Enzo").build();

        List<Product> carProducts = Arrays.asList(mercedes, bmw, ferrari);
        productService.saveBulk(carProducts);

        Product apple = Product.builder().category(phoneCategory).price(BigDecimal.valueOf(1000)).title("Iphone 12").build();
        Product samsung = Product.builder().category(phoneCategory).price(BigDecimal.valueOf(800)).title("Samsung Galaxy Note 10").build();
        Product huawei = Product.builder().category(phoneCategory).price(BigDecimal.valueOf(600)).title("Huawei Mate 20 pro").build();

        List<Product> phoneProducts = Arrays.asList(apple, samsung, huawei);
        productService.saveBulk(phoneProducts);

        Product inci = Product.builder().category(shoesCategory).price(BigDecimal.valueOf(10)).title("Inci Classic").build();
        Product hotic = Product.builder().category(shoesCategory).price(BigDecimal.valueOf(7)).title("Hotic Casual").build();

        List<Product> shoeProducts = Arrays.asList(inci, hotic);
        productService.saveBulk(shoeProducts);

        Product appleHeadPhone = Product.builder().category(headPhoneCategory).price(BigDecimal.valueOf(40)).title("AirPods 2").build();
        Product huaweiHeadPhone = Product.builder().category(headPhoneCategory).price(BigDecimal.valueOf(25)).title("FreeBuds").build();

        List<Product> headPhoneProducts = Arrays.asList(appleHeadPhone, huaweiHeadPhone);
        productService.saveBulk(headPhoneProducts);

        Campaign computerCampaign = Campaign.builder().category(computerCategory).discount(BigDecimal.valueOf(10)).minPurchaseLimit(BigDecimal.valueOf(5)).discountFromType(DiscountFromType.RATE).build();
        Campaign computerCampaign2 = Campaign.builder().category(computerCategory).discount(BigDecimal.valueOf(15)).minPurchaseLimit(BigDecimal.valueOf(4)).discountFromType(DiscountFromType.RATE).build();
        Campaign computerCampaign3 = Campaign.builder().category(computerCategory).discount(BigDecimal.valueOf(75)).minPurchaseLimit(BigDecimal.valueOf(3)).discountFromType(DiscountFromType.AMOUNT).build();

        Campaign carCampaign = Campaign.builder().category(carCategory).discount(BigDecimal.valueOf(25)).minPurchaseLimit(BigDecimal.valueOf(1)).discountFromType(DiscountFromType.RATE).build();
        Campaign headPhoneCampaign = Campaign.builder().category(headPhoneCategory).discount(BigDecimal.valueOf(7)).minPurchaseLimit(BigDecimal.valueOf(3)).discountFromType(DiscountFromType.AMOUNT).build();

        List<Campaign> campaigns = Arrays.asList(computerCampaign, computerCampaign2, computerCampaign3, carCampaign, headPhoneCampaign);
        campaignService.saveBulk(campaigns);

        Coupon amountCoupon = Coupon.builder().discount(BigDecimal.valueOf(30)).minPurchaseAmount(BigDecimal.valueOf(150)).discountFromType(DiscountFromType.AMOUNT).build();
        Coupon couponRate = Coupon.builder().discount(BigDecimal.valueOf(30)).minPurchaseAmount(BigDecimal.valueOf(150)).discountFromType(DiscountFromType.RATE).build();

        couponService.saveBulk(Arrays.asList(amountCoupon, couponRate));
    }
}
