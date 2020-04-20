package com.project.shoppingcart.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class BaseResponse {

    protected int responseCode = HttpStatus.OK.value();
    protected String message = "SUCCESS";
}
