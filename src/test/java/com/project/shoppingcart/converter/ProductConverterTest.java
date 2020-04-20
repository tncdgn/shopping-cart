package com.project.shoppingcart.converter;

import com.project.shoppingcart.entity.Category;
import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.model.ProductModel;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductConverterTest {

    @Test
    public void shouldConvertToEntity() {
        Category category = Category.builder().build();
        ProductModel productModel = ProductModel.builder()
                .category(category)
                .price(BigDecimal.valueOf(10))
                .title("title").build();

        ProductConverter productConverter = new ProductConverter();
        Product product = productConverter.toEntity(productModel);

        assertEquals(product.getCategory(), productModel.getCategory());
        assertEquals(product.getPrice(), productModel.getPrice());
        assertEquals(product.getTitle(), productModel.getTitle());
    }

    @Test
    public void shouldConvertToModel() {
        Category category = Category.builder().title("category").build();
        Product product = Product.builder()
                .id(1L)
                .category(category)
                .price(BigDecimal.valueOf(10))
                .title("title").build();

        ProductConverter productConverter = new ProductConverter();
        ProductModel productModel = productConverter.toModel(product);

        assertEquals(product.getCategory().getTitle(), productModel.getCategoryTitle());
        assertEquals(product.getPrice(), productModel.getPrice());
        assertEquals(product.getTitle(), productModel.getTitle());
        assertEquals(product.getId(), productModel.getId());
    }
}