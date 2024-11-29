package com.assignment.test.dto;

import lombok.Data;

@Data
public class ProductDto {

	private String productName;

	private Integer price;

	private CategoryDto categoryDto;
}
