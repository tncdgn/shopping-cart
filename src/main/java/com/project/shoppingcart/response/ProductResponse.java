package com.project.shoppingcart.response;

import com.project.shoppingcart.model.ProductModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponse extends BaseResponse {
    private List<ProductModel> productModels = new ArrayList<>();
}
