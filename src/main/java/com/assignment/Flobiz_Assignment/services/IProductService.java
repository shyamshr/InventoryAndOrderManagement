package com.assignment.Flobiz_Assignment.services;

import com.assignment.Flobiz_Assignment.exceptions.InvalidDataException;
import com.assignment.Flobiz_Assignment.exceptions.ProductNotFoundException;
import com.assignment.Flobiz_Assignment.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    Product addNewProduct(Product product) throws Exception;
    Product updateSingleProduct(Long productId,Product product) throws Exception;
    Page<Product> getAllProducts(String query, int sizeOfPage, int offset) throws Exception;
    void deleteSingleProduct(Long productId) throws Exception;
}
