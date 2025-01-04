package com.assignment.Flobiz_Assignment.services;

import com.assignment.Flobiz_Assignment.dtos.RequestItems;
import com.assignment.Flobiz_Assignment.exceptions.InvalidDataException;
import com.assignment.Flobiz_Assignment.exceptions.OrderNotFoundException;
import com.assignment.Flobiz_Assignment.exceptions.OutOfStockException;
import com.assignment.Flobiz_Assignment.exceptions.ProductNotFoundException;
import com.assignment.Flobiz_Assignment.models.Order;
import com.assignment.Flobiz_Assignment.models.OrderItem;
import com.assignment.Flobiz_Assignment.models.Product;
import com.assignment.Flobiz_Assignment.repositories.OrderItemRepo;
import com.assignment.Flobiz_Assignment.repositories.OrderRepo;
import com.assignment.Flobiz_Assignment.repositories.ProductRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createNewOrder(List<RequestItems> items)
            throws Exception {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0;
        for(RequestItems item:items){
            OrderItem orderItem = new OrderItem();
            Optional<Product> optionalProduct = productRepo.
                            findByIdForUpdateAndIsDeletedFalse(item.getProductId());
            if(optionalProduct.isEmpty()){
                throw new ProductNotFoundException
                        ("No Product was found with id: "+item.getProductId());
            }
            Product product = optionalProduct.get();
            if(item.getQuantity()<=0){
                throw new InvalidDataException
                        ("Product with id: "+item.getProductId()+" should have requested quantity greater than zero");
            }
            if(item.getQuantity()>product.getQuantityInStock()){
                throw new OutOfStockException
                        ("Product with id: "+item.getProductId()+" is out of stock");
            }
            product.setQuantityInStock(product.getQuantityInStock() - item.getQuantity());
            productRepo.save(product);
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setNameAtOrder(product.getName());
            orderItem.setSkuAtOrder(product.getSku());
            orderItem.setPriceAtOrder(product.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
            totalPrice +=product.getPrice() * item.getQuantity();
        }
        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);

        return orderRepo.save(order);
    }

    @Override
    public Order getSingleOrder(Long orderId) throws Exception {
        Optional<Order> optionalOrder =  Optional.
                ofNullable(orderRepo.findOrderById(orderId));
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException("No Order was found with id: "+orderId);
        }
        return optionalOrder.get();
    }

    @Override
    public Page<Order> getAllOrders(int sizeOfPage, int offset) throws Exception {
        return orderRepo.findAll(PageRequest.of(offset/sizeOfPage,sizeOfPage));
    }
}
