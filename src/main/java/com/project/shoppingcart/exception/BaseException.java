package com.project.shoppingcart.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseException extends RuntimeException {
    protected HttpStatus code;
    protected String messageKey;
    protected List<Object> params = new ArrayList<>();
}
