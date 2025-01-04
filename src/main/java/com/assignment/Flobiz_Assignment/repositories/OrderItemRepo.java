package com.assignment.Flobiz_Assignment.repositories;

import com.assignment.Flobiz_Assignment.models.Order;
import com.assignment.Flobiz_Assignment.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<Order,Long> {
    OrderItem save(OrderItem orderItem);
}
