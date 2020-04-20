package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.CartItemDao;
import com.project.shoppingcart.entity.CartItem;
import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.entity.ShoppingCart;
import com.project.shoppingcart.exception.ProductNotFoundException;
import com.project.shoppingcart.model.AddCartItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService extends BaseService<CartItem> {

    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public void save(CartItem cartItem) {
        cartItemDao.save(cartItem);
    }

    @Override
    public Optional<CartItem> findById(long id) {
        return cartItemDao.findById(id);
    }

    public CartItem prepareCartItem(AddCartItemModel cartItemModel, ShoppingCart shoppingCart) {
        CartItem cartItem = new CartItem();

        Product product = productService.findById(cartItemModel.getProductId()).orElseThrow(() -> new ProductNotFoundException("product.notFound"));
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemModel.getQuantity());
        cartItem.setUnitPrice(product.getPrice());
        cartItem.prepareTotalPrice();
        cartItem.setShoppingCart(shoppingCart);
        return cartItem;
    }
}
