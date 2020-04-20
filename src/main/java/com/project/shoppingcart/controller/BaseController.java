package com.project.shoppingcart.controller;

import com.project.shoppingcart.converter.ProductConverter;
import com.project.shoppingcart.exception.BaseException;
import com.project.shoppingcart.response.BaseResponse;
import com.project.shoppingcart.response.ExceptionResponse;
import com.project.shoppingcart.service.*;
import com.project.shoppingcart.validator.CartItemValidator;
import com.project.shoppingcart.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    protected ShoppingCartService shoppingCartService;

    @Autowired
    protected CartItemService cartItemService;

    @Autowired
    protected CouponService couponService;

    @Autowired
    protected CampaignService campaignService;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected ProductValidator productValidator;

    @Autowired
    protected ProductConverter productConverter;

    @Autowired
    protected CartItemValidator cartItemValidator;

    protected ResponseEntity<BaseResponse> handleException(Exception e) {
        String message;
        int responseCode;

        if (e instanceof BaseException) {
            BaseException ex = (BaseException) e;
            message = messageSource.getMessage(ex.getMessageKey(), new List[]{ex.getParams()}, LocaleContextHolder.getLocale());
            responseCode = ex.getCode().value();

        } else {
            message = e.getMessage();
            responseCode = HttpStatus.BAD_REQUEST.value();
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setResponseCode(responseCode);
        exceptionResponse.setMessage(message);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
