package com.project.shoppingcart.validator;

import com.project.shoppingcart.exception.InvalidRequestException;
import com.project.shoppingcart.model.ProductModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
public class ProductValidatorTest {

    @InjectMocks
    private ProductValidator productValidator;

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfTitleIsNull() {
        ProductModel productModel = new ProductModel();

        productValidator.validate(productModel);
    }

    @Test
    public void shouldValidate() {
        ProductModel productModel = new ProductModel();
        productModel.setTitle("title");

        try {
            productValidator.validate(productModel);
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfIdInvalid() {

        productValidator.validateId(0L);
    }

    @Test
    public void shouldValidateId() {
        try {
            productValidator.validateId(123l);
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfISize() {

        productValidator.validateSize(0);
    }

    @Test
    public void shouldValidateSize() {
        try {
            productValidator.validateSize(123);
        } catch (Exception e) {
            fail();
        }
    }
}