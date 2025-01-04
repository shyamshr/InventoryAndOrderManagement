package com.assignment.Flobiz_Assignment.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private String sku;
    private Double price;
    private Integer quantityInStock;
    private Boolean isDeleted;
}
