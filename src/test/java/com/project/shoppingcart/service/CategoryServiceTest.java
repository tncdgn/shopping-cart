package com.project.shoppingcart.service;

import com.project.shoppingcart.dao.CategoryDao;
import com.project.shoppingcart.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;


@RunWith(PowerMockRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryDao categoryDao;

    @Test
    public void shouldSave() {
        Category category = new Category();

        categoryService.save(category);

        verify(categoryDao).save(category);
    }

    @Test
    public void shouldFindById() {
        categoryService.findById(1l);

        verify(categoryDao).findById(1l);
    }
}