package com.assignment.Flobiz_Assignment.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private List<OrderItemDto> orderItemDtos;
}
