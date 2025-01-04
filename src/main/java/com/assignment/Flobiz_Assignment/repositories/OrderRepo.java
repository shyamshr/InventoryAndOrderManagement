package com.assignment.Flobiz_Assignment.repositories;

import com.assignment.Flobiz_Assignment.models.Order;
import com.assignment.Flobiz_Assignment.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    Order save(Order order);
    Order findOrderById(long id);
    Page<Order> findAll(Pageable pageable);
}
