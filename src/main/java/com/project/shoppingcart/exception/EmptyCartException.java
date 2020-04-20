package com.project.shoppingcart.exception;

import org.springframework.http.HttpStatus;

public class EmptyCartException extends BaseException {

    public EmptyCartException() {
        this.code = HttpStatus.OK;
        this.messageKey = "shoppingCart.empty";
    }
}
