package com.assignment.Flobiz_Assignment.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private List<RequestItems> items;
}
