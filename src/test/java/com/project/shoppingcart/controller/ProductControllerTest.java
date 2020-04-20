package com.project.shoppingcart.controller;

import com.project.shoppingcart.converter.ProductConverter;
import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.exception.InvalidRequestException;
import com.project.shoppingcart.service.ProductService;
import com.project.shoppingcart.validator.ProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LocaleContextHolder.class})
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductValidator productValidator;

    @Mock
    private ProductService productService;

    @Mock
    private ProductConverter productConverter;

    @Mock
    private MessageSource messageSource;

    @Mock
    private Locale locale;

    @Test
    public void shouldThrowExceptionIfProductIdIsNotValid() {
        Object value = new Object();
        Mockito.doThrow(new InvalidRequestException("product.id.notValid", value)).when(productValidator).validateId(1L);
        mockStatic(LocaleContextHolder.class);
        when(LocaleContextHolder.getLocale()).thenReturn(locale);

        productController.findProductById(1l);

        verifyZeroInteractions(productService);
        verifyZeroInteractions(productConverter);

        verify(messageSource).getMessage("product.id.notValid", new List[]{Arrays.asList(value)}, locale);
    }

    @Test
    public void shouldThrowExceptionIfProductNotFound() {
        Object value = new Object();
        doThrow(new InvalidRequestException("product.id.notValid", value)).when(productValidator).validateId(1L);
        mockStatic(LocaleContextHolder.class);
        when(LocaleContextHolder.getLocale()).thenReturn(locale);

        when(productService.findById(1l)).thenReturn(null);

        productController.findProductById(1l);

        verifyZeroInteractions(productConverter);

        verify(messageSource).getMessage("product.id.notValid", new List[]{Arrays.asList(value)}, locale);
        verify(productValidator).validateId(1l);
    }

    @Test
    public void shouldFindByProductId() {
        Product product = Product.builder().build();

        Optional<Product> optionalProduct = Optional.ofNullable(product);
        when(productService.findById(1l)).thenReturn(optionalProduct);

        productController.findProductById(1l);

        verify(productValidator).validateId(1l);
        verify(productConverter).toModel(product);
        verifyZeroInteractions(messageSource);
    }

    @Test
    public void shouldThrowExceptionIfThereIsNoProduct() {
        Object value = new Object();
        doThrow(new InvalidRequestException("product.emptyList", value)).when(productValidator).validateSize(0);
        mockStatic(LocaleContextHolder.class);
        when(LocaleContextHolder.getLocale()).thenReturn(locale);

        when(productService.findAll()).thenReturn(Collections.EMPTY_LIST);
        productController.findAll();

        verifyZeroInteractions(productConverter);

        verify(messageSource).getMessage("product.emptyList", new List[]{Arrays.asList(value)}, locale);
    }

    @Test
    public void shouldFindAllProduct() {
        Product product = Product.builder().build();

        when(productService.findAll()).thenReturn(Arrays.asList(product));
        productController.findAll();

        verify(productConverter).toModel(product);
        verifyZeroInteractions(messageSource);
    }
}