package com.project.shoppingcart.converter;

import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.model.ProductModel;
import org.springframework.stereotype.Service;

@Service
public class ProductConverter extends BaseConverter<Product, ProductModel> {

    @Override
    public Product toEntity(ProductModel productModel) {
        Product product = Product.builder()
                .category(productModel.getCategory())
                .price(productModel.getPrice())
                .title(productModel.getTitle()).build();

        return product;
    }

    @Override
    public ProductModel toModel(Product product) {
        ProductModel productModel = ProductModel.builder()
                .id(product.getId())
                .price(product.getPrice())
                .categoryTitle(product.getCategory().getTitle())
                .title(product.getTitle()).build();

        return productModel;
    }
}
