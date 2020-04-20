package com.project.shoppingcart.controller;

import com.project.shoppingcart.entity.Product;
import com.project.shoppingcart.exception.ProductNotFoundException;
import com.project.shoppingcart.model.ProductModel;
import com.project.shoppingcart.response.BaseResponse;
import com.project.shoppingcart.response.ProductResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/product")
@RestController
public class ProductController extends BaseController {

    @ApiOperation("Find Product By Id")
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BaseResponse> findProductById(@PathVariable Long productId) {
        ResponseEntity<BaseResponse> response = null;
        ProductResponse productResponse = new ProductResponse();
        try {
            productValidator.validateId(productId);

            productResponse.setProductModels(Arrays.asList(prepareModel(productId)));

            response = new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = handleException(e);
        }
        return response;
    }

    @ApiOperation("Find All Product")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BaseResponse> findAll() {
        ResponseEntity<BaseResponse> response = null;
        ProductResponse productResponse = new ProductResponse();
        try {
            List<Product> products = productService.findAll();
            productValidator.validateSize(products.size());

            List<ProductModel> productModels = products.stream().map(product -> productConverter.toModel(product)).collect(Collectors.toList());
            productResponse.setProductModels(productModels);

            response = new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (Exception e) {
            response = handleException(e);
        }
        return response;
    }

    private ProductModel prepareModel(long productId) {
        Optional<Product> optionalProduct = productService.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException("product.notFound"));

        return productConverter.toModel(product);
    }
}
