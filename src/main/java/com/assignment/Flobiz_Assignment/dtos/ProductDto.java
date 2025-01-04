package com.assignment.Flobiz_Assignment.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String sku;
    private Double price;
    private Integer quantityInStock;
}
