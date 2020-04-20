package com.project.shoppingcart.controller;

import com.project.shoppingcart.model.AddCartItemModel;
import com.project.shoppingcart.response.BaseResponse;
import com.project.shoppingcart.response.CartItemResponse;
import com.project.shoppingcart.response.ShoppingCartResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/shoppingCart")
@RestController
public class ShoppingCartController extends BaseController {

    @ApiOperation("Add Item To Cart")
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResponse> addItem(@RequestBody AddCartItemModel cartItemModel) {
        ResponseEntity<BaseResponse> response = null;
        BaseResponse productResponse = new CartItemResponse();
        try {
            cartItemValidator.validate(cartItemModel);

            shoppingCartService.addItem(cartItemModel);
            response = new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = handleException(e);
        }
        return response;
    }

    @ApiOperation("Print Current Cart")
    @RequestMapping(value = "/print", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BaseResponse> print() {
        ResponseEntity<BaseResponse> response = null;
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
        try {

            shoppingCartResponse.setShoppingCartModel(shoppingCartService.print());
            response = new ResponseEntity<>(shoppingCartResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = handleException(e);
        }
        return response;
    }
}
