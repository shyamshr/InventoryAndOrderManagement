package com.assignment.Flobiz_Assignment.services;

import com.assignment.Flobiz_Assignment.dtos.RequestItems;
import com.assignment.Flobiz_Assignment.exceptions.InvalidDataException;
import com.assignment.Flobiz_Assignment.exceptions.OrderNotFoundException;
import com.assignment.Flobiz_Assignment.exceptions.OutOfStockException;
import com.assignment.Flobiz_Assignment.exceptions.ProductNotFoundException;
import com.assignment.Flobiz_Assignment.models.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    Order createNewOrder(List<RequestItems> items) throws Exception;
    Order getSingleOrder(Long orderId) throws Exception;
    Page<Order> getAllOrders(int sizeOfPage,int offset) throws Exception;
}
