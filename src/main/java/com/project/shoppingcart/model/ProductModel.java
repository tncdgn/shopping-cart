package com.project.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.shoppingcart.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel extends BaseModel {

    private Long id;
    private String categoryTitle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Category category;
    private String title;
    private BigDecimal price;
}
