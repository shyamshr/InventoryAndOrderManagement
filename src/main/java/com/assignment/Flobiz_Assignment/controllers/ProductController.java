package com.assignment.Flobiz_Assignment.controllers;

import com.assignment.Flobiz_Assignment.dtos.ProductDto;
import com.assignment.Flobiz_Assignment.exceptions.InvalidDataException;
import com.assignment.Flobiz_Assignment.exceptions.ProductNotFoundException;
import com.assignment.Flobiz_Assignment.services.IProductService;
import com.assignment.Flobiz_Assignment.utils.CommonUtils;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This controller will always answer to /products.
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @PostMapping("")
    public ResponseEntity<ProductDto> addNewProduct
            (@RequestBody ProductDto productDto)
            throws Exception {
        try {
            ProductDto productDtoResponse = CommonUtils.getProductDtoFromProducts
                    (iProductService.addNewProduct
                            (CommonUtils.getProductFromProductDto(productDto)));
            ResponseEntity<ProductDto> productsResponseEntity =
                    new ResponseEntity<>(productDtoResponse, HttpStatus.CREATED);
            return productsResponseEntity;
        } catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDto> updateSingleProduct
            (@PathVariable("productId") Long productId, @RequestBody ProductDto productDto)
            throws Exception {
        try {
            ProductDto productDtoResponse = CommonUtils.
                    getProductDtoFromProducts(iProductService.
                            updateSingleProduct(productId, CommonUtils.getProductFromProductDto(productDto)));
            ResponseEntity<ProductDto> productsResponseEntity =
                    new ResponseEntity<>(productDtoResponse, HttpStatus.OK);
            return productsResponseEntity;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<ProductDto>> getAllProducts
            (@RequestParam@Nullable String query, @RequestParam int sizeOfPage, @RequestParam int offset) throws Exception {
        try {
            return new ResponseEntity<>
                    (CommonUtils.getProductDtoPageFromProductPage(iProductService.
                            getAllProducts(query, sizeOfPage, offset)), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteSingleProduct
            (@PathVariable("productId") Long productId)
            throws Exception {
        try {
            iProductService.deleteSingleProduct(productId);
            ResponseEntity<Void> productsResponseEntity = new ResponseEntity<>(HttpStatus.OK);
            return productsResponseEntity;
        } catch (Exception e) {
            throw e;
        }
    }
}
