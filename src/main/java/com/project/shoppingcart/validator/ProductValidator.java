package com.project.shoppingcart.validator;

import com.project.shoppingcart.model.ProductModel;
import com.project.shoppingcart.validator.util.Validators;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator extends BaseValidator<ProductModel> {

    @Override
    public void validate(ProductModel request) {
        validateRequest(request.getTitle(), "product.name.notNull", Validators.notBlank());
    }

    public void validateId(Long productId) {
        validateRequest(productId.intValue(), "product.id.notValid", Validators.greaterOrEqualTo(1));
    }

    public void validateSize(int size) {
        validateRequest(size, "product.emptyList", Validators.greaterOrEqualTo(1));
    }
}
