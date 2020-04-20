package com.project.shoppingcart.service;

import com.project.shoppingcart.constant.ShoppingCartConstant;
import com.project.shoppingcart.chain.ChainProcessorModel;
import com.project.shoppingcart.dao.ShoppingCartDao;
import com.project.shoppingcart.entity.Campaign;
import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.entity.Coupon;
import com.project.shoppingcart.entity.ShoppingCart;
import com.project.shoppingcart.exception.EmptyCartException;
import com.project.shoppingcart.model.AddCartItemModel;
import com.project.shoppingcart.model.CartItemModel;
import com.project.shoppingcart.model.ShoppingCartModel;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService extends BaseService<ShoppingCart> {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> findById(long id) {
        return shoppingCartDao.findById(id);
    }

    @Transactional
    public void addItem(AddCartItemModel cartItemModel) {
        ShoppingCart shoppingCart = getCurrentCart();

        if (shoppingCart == null) {
            shoppingCart = createNewCart();
        }

        chainStarter.startChain(prepareModel(cartItemModel, shoppingCart));
        save(shoppingCart);
    }

    public ShoppingCart getCurrentCart() {
        return shoppingCartDao.getByBuyerId(ShoppingCartConstant.CURRENT_BUYER_ID);
    }

    public ShoppingCart createNewCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        save(shoppingCart);

        return shoppingCart;
    }

    public ShoppingCartModel print() {
        ShoppingCart shoppingCart = getCurrentCart();
        if (shoppingCart == null) {
            throw new EmptyCartException();
        }
        if (CollectionUtils.isEmpty(shoppingCart.getCartItems())) {
            throw new EmptyCartException();
        }

        return prepareModelFrom(shoppingCart);
    }

    private ShoppingCartModel prepareModelFrom(ShoppingCart shoppingCart) {
        ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
        shoppingCartModel.setCouponDiscount(shoppingCart.getCouponDiscount());
        shoppingCartModel.setDeliveryCost(shoppingCart.getDeliveryCost());
        shoppingCartModel.setTotalPrice(shoppingCart.getTotalPrice());

        List<CartItemModel> cartItemModels = shoppingCart.getCartItems().stream().map(cartItem -> cartItemConverter.toModel(cartItem)).collect(Collectors.toList());
        cartItemModels.sort(Comparator.comparing(CartItemModel::getCategoryName).thenComparing(CartItemModel::getProductName));

        shoppingCartModel.setCartItemModels(cartItemModels);

        return shoppingCartModel;
    }

    private ChainProcessorModel prepareModel(AddCartItemModel cartItemModel, ShoppingCart shoppingCart) {
        ChainProcessorModel chainProcessorModel = new ChainProcessorModel();

        CartItem cartItem = cartItemService.prepareCartItem(cartItemModel, shoppingCart);
        shoppingCart.getCartItems().add(cartItem);

        List<Campaign> campaigns = campaignService.findByCategory(cartItem.getProduct().getCategory());
        List<Coupon> coupons = couponService.findAll();

        chainProcessorModel.setCampaigns(campaigns);
        chainProcessorModel.setCoupons(coupons);
        chainProcessorModel.setCartItem(cartItem);
        chainProcessorModel.setShoppingCart(shoppingCart);

        return chainProcessorModel;
    }
}
