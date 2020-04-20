package com.project.shoppingcart.controller;

import com.project.shoppingcart.exception.InvalidRequestException;
import com.project.shoppingcart.model.AddCartItemModel;
import com.project.shoppingcart.service.ShoppingCartService;
import com.project.shoppingcart.validator.CartItemValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LocaleContextHolder.class})
public class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private CartItemValidator cartItemValidator;

    @Mock
    private Locale locale;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private MessageSource messageSource;

    @Test
    public void shouldThrowExceptionIfRequestIsNotValid() {
        AddCartItemModel addCartItemModel = new AddCartItemModel();

        Object value = new Object();
        Mockito.doThrow(new InvalidRequestException("product.id.notValid", value)).when(cartItemValidator).validate(addCartItemModel);
        mockStatic(LocaleContextHolder.class);
        Mockito.when(LocaleContextHolder.getLocale()).thenReturn(locale);

        shoppingCartController.addItem(addCartItemModel);

        verifyZeroInteractions(shoppingCartService);

        verify(messageSource).getMessage("product.id.notValid", new List[]{Arrays.asList(value)}, locale);
    }

    @Test
    public void shouldAddCartItem() {
        AddCartItemModel addCartItemModel = new AddCartItemModel();

        shoppingCartController.addItem(addCartItemModel);

        verify(shoppingCartService).addItem(addCartItemModel);
        verify(cartItemValidator).validate(addCartItemModel);

        verifyZeroInteractions(messageSource);
    }

    @Test
    public void shouldNotPrintCartIfExceptionOccurs() {
        Object value = new Object();
        doThrow(new InvalidRequestException("exception", value)).when(shoppingCartService).print();
        mockStatic(LocaleContextHolder.class);
        Mockito.when(LocaleContextHolder.getLocale()).thenReturn(locale);

        shoppingCartController.print();

        verify(messageSource).getMessage("exception", new List[]{Arrays.asList(value)}, locale);
    }

    @Test
    public void shouldPrintCurrentCart() {
        shoppingCartController.print();

        verify(shoppingCartService).print();
    }
}