package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.ProductDao;
import com.project.shoppingcart.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDao productDao;

    @Test
    public void shouldSave() {
        Product product = new Product();

        productService.save(product);

        verify(productDao).save(product);
    }

    @Test
    public void shouldFindById() {
        productService.findById(1l);

        verify(productDao).findById(1l);
    }

    @Test
    public void shouldFindAll() {
        productService.findAll();

        verify(productDao).findAll();
    }
}