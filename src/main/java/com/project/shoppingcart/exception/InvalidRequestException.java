package com.project.shoppingcart.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends BaseException {

    public InvalidRequestException(String messageKey, Object value) {
        this.code = HttpStatus.BAD_REQUEST;
        this.messageKey = messageKey;
        this.params.add(value);
    }
}
