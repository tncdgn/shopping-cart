package com.project.shoppingcart.validator;

import com.project.shoppingcart.exception.InvalidRequestException;
import com.project.shoppingcart.model.AddCartItemModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
public class CartItemValidatorTest {

    @InjectMocks
    private CartItemValidator cartItemValidator;

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfProductIdInvalid() {
        AddCartItemModel addCartItemModel = new AddCartItemModel();
        addCartItemModel.setProductId(0);

        cartItemValidator.validate(addCartItemModel);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfQuantityInvalid() {
        AddCartItemModel addCartItemModel = new AddCartItemModel();
        addCartItemModel.setProductId(2);
        addCartItemModel.setQuantity(0);

        cartItemValidator.validate(addCartItemModel);
    }

    @Test
    public void shouldValidate() {
        AddCartItemModel addCartItemModel = new AddCartItemModel();
        addCartItemModel.setProductId(2);
        addCartItemModel.setQuantity(1);

        try {
            cartItemValidator.validate(addCartItemModel);
        } catch (Exception e) {
            fail();
        }
    }
}