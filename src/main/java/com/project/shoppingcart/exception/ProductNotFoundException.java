package com.project.shoppingcart.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException {

    public ProductNotFoundException(String messageKey) {
        this.code = HttpStatus.OK;
        this.messageKey = messageKey;
    }
}
