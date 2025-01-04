package com.assignment.Flobiz_Assignment.repositories;

import com.assignment.Flobiz_Assignment.models.Product;
import jakarta.persistence.Lob;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.From;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product save(Product product);
    Product findProductByIdAndIsDeletedFalse(long id); // this method is used to find active products with id
    Page<Product> findByIsDeletedFalseAndNameContainingOrIsDeletedFalseAndSkuContaining(String name, String sku,Pageable pageable);
    Page<Product> findAllByIsDeletedFalse(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT p FROM Product p WHERE p.id = :productId AND p.isDeleted = false")
    Optional<Product> findByIdForUpdateAndIsDeletedFalse(@Param("productId") Long productId);
}

