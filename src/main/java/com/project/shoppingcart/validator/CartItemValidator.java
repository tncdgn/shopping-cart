package com.project.shoppingcart.validator;

import com.project.shoppingcart.model.AddCartItemModel;
import com.project.shoppingcart.validator.util.Validators;
import org.springframework.stereotype.Service;

@Service
public class CartItemValidator extends BaseValidator<AddCartItemModel> {


    @Override
    public void validate(AddCartItemModel request) {
        validateRequest((int) request.getProductId(), "product.id.notValid", Validators.greaterOrEqualTo(1));
        validateRequest(request.getQuantity(), "product.quantity.notValid", Validators.greaterOrEqualTo(1));
    }
}
