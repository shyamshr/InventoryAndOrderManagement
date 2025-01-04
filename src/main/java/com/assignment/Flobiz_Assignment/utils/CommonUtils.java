package com.assignment.Flobiz_Assignment.utils;

import com.assignment.Flobiz_Assignment.dtos.OrderItemDto;
import com.assignment.Flobiz_Assignment.dtos.OrderResponseDto;
import com.assignment.Flobiz_Assignment.dtos.ProductDto;
import com.assignment.Flobiz_Assignment.models.Order;
import com.assignment.Flobiz_Assignment.models.OrderItem;
import com.assignment.Flobiz_Assignment.models.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    public static Product getProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setSku(productDto.getSku());
        product.setPrice(productDto.getPrice());
        product.setQuantityInStock(productDto.getQuantityInStock());
        product.setIsDeleted(false);
        return product;
    }
    public static ProductDto getProductDtoFromProducts(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setSku(product.getSku());
        productDto.setPrice(product.getPrice());
        productDto.setQuantityInStock(product.getQuantityInStock());
        return productDto;
    }
    public static Page<ProductDto> getProductDtoPageFromProductPage(Page<Product> productPage){
        if(productPage == null)
            return null;
        return productPage.map(CommonUtils::getProductDtoFromProducts);
    }
    public static Page<OrderResponseDto> getOrderResponseDtoPageFromOrderPage(Page<Order> orderPage){
        if(orderPage == null)
            return null;
        return orderPage.map(CommonUtils::getOrderResponseDtoFromOrder);
    }
    public static OrderResponseDto getOrderResponseDtoFromOrder(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        List<OrderItemDto> orderItemDtos = getOrderItemDtos(order);
        orderResponseDto.setOrderItemDtos(orderItemDtos);
        return orderResponseDto;

    }

    private static List<OrderItemDto> getOrderItemDtos(Order order) {
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for(OrderItem orderItem: order.getItems()){
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProductId(orderItem.getProduct().getId());
            orderItemDto.setProductName(orderItem.getNameAtOrder());
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderItemDto.setPriceAtOrder(orderItem.getPriceAtOrder());
            orderItemDtos.add(orderItemDto);
        }
        return orderItemDtos;
    }

}
