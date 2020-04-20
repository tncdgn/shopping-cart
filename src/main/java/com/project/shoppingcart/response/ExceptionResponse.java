package com.project.shoppingcart.response;

import lombok.Data;

@Data
public class ExceptionResponse extends BaseResponse {

    private static final long serialVersionUID = 4489380815107641118L;

    protected String message;
}
