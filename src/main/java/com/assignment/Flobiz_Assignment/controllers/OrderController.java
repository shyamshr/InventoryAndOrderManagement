package com.assignment.Flobiz_Assignment.controllers;

import com.assignment.Flobiz_Assignment.dtos.OrderRequestDto;
import com.assignment.Flobiz_Assignment.dtos.OrderResponseDto;
import com.assignment.Flobiz_Assignment.exceptions.InvalidDataException;
import com.assignment.Flobiz_Assignment.exceptions.OrderNotFoundException;
import com.assignment.Flobiz_Assignment.exceptions.OutOfStockException;
import com.assignment.Flobiz_Assignment.exceptions.ProductNotFoundException;
import com.assignment.Flobiz_Assignment.models.Order;
import com.assignment.Flobiz_Assignment.services.IOrderService;
import com.assignment.Flobiz_Assignment.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This controller will always answer to /orders.
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;


    @PostMapping("")
    public ResponseEntity<OrderResponseDto> createNewOrder
            (@RequestBody OrderRequestDto orderRequestDto)
            throws Exception {
        try {
            ResponseEntity<OrderResponseDto> orderResponseEntity = new ResponseEntity<>
                    (CommonUtils.getOrderResponseDtoFromOrder(iOrderService.createNewOrder(orderRequestDto.getItems())),
                            HttpStatus.CREATED);
            return orderResponseEntity;
        }catch (Exception e){
            throw e;
        }
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getSingleOrder
            (@PathVariable Long orderId)
            throws Exception {
        try {
            ResponseEntity<OrderResponseDto> orderResponseEntity = new ResponseEntity<>
                    (CommonUtils.getOrderResponseDtoFromOrder(iOrderService.getSingleOrder(orderId)),
                            HttpStatus.OK);
            return orderResponseEntity;
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<OrderResponseDto>> getAllOrders
            (@RequestParam int sizeOfPage,@RequestParam int offset) throws Exception{
        try {
            return new ResponseEntity<>
                    (CommonUtils.getOrderResponseDtoPageFromOrderPage(iOrderService.
                            getAllOrders(sizeOfPage, offset)), HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }

}
