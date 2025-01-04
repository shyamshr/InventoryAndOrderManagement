package com.assignment.Flobiz_Assignment.services;

import com.assignment.Flobiz_Assignment.exceptions.InvalidDataException;
import com.assignment.Flobiz_Assignment.exceptions.ProductNotFoundException;
import com.assignment.Flobiz_Assignment.models.Product;
import com.assignment.Flobiz_Assignment.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product addNewProduct(Product product)
            throws Exception{
        if (product.getName() == null || product.getName().isEmpty()
                || product.getSku() == null || product.getSku().isEmpty()
                || product.getPrice() == null || product.getQuantityInStock() == null
                || product.getQuantityInStock()<0 ) {
            throw new InvalidDataException("Provided product data is Invalid");
        }
        return productRepo.save(product);
    }

    @Override
    public Product updateSingleProduct(Long productId, Product product)
            throws Exception {
        Optional<Product> existingProduct = Optional.
                ofNullable(productRepo.
                        findProductByIdAndIsDeletedFalse(productId));
        if(existingProduct.isEmpty())
            throw new ProductNotFoundException("No Product was found with id: "+productId);
        Product updatedProduct = this.
                convertExistingProductToUpdatedProduct(existingProduct.get(),product);
        return productRepo.save(updatedProduct);
    }


    @Override
    public Page<Product> getAllProducts(String query,int sizeOfPage, int offset) throws Exception{
        if(query == null || query.isEmpty()){
            System.out.println("here");
            return productRepo.
                    findAllByIsDeletedFalse
                            (PageRequest.of(offset/sizeOfPage,sizeOfPage));
        }
        return productRepo.
                findByIsDeletedFalseAndNameContainingOrIsDeletedFalseAndSkuContaining
                        (query,query,PageRequest.of(offset/sizeOfPage,sizeOfPage));
    }

    @Override
    public void deleteSingleProduct(Long productId)
            throws Exception {
        Optional<Product> optionalProduct = Optional.
                ofNullable(productRepo.
                        findProductByIdAndIsDeletedFalse(productId));
        if(optionalProduct.isEmpty())
            throw new ProductNotFoundException("No Product was found with id: "+productId);
        optionalProduct.get().setIsDeleted(false);
        productRepo.save(optionalProduct.get());

    }
    private Product convertExistingProductToUpdatedProduct(Product existingProduct, Product product){
        if(product.getName() != null
                && !product.getName().isEmpty()){
            existingProduct.
                    setName(product.getName());
        }
        if(product.getSku() != null
                && !product.getSku().isEmpty()){
            existingProduct.
                    setSku(product.getSku());
        }
        if(product.getPrice()!=null){
            existingProduct.
                    setPrice(product.getPrice());
        }
        if(product.getQuantityInStock()!=null
                && product.getQuantityInStock()>=0) {
            existingProduct.
                    setQuantityInStock(product.getQuantityInStock());
        }

        return existingProduct;
    }
}
