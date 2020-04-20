package com.project.shoppingcart.validator;

import com.project.shoppingcart.exception.InvalidRequestException;
import com.project.shoppingcart.model.BaseModel;
import org.hamcrest.Matcher;

public abstract class BaseValidator<T extends BaseModel> {

    public abstract void validate(T request);

    protected void validateRequest(Object value, String messageKey, Matcher<?> matcher) {
        if (!matcher.matches(value)) {
            throw new InvalidRequestException(messageKey, value);
        }
    }
}
