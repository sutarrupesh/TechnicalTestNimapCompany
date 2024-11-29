package com.assignment.test.service;

import java.util.List;

import com.assignment.test.dto.ProductDto;

public interface ProductService {

	public ProductDto createProduct(ProductDto productDto, Integer categoryId);

	public ProductDto updateProduct(ProductDto productDto, Integer productId);

	public ProductDto getProductById(Integer productId);

	public List<ProductDto> getAllProduct(Integer pageNumber, Integer pageSize);

	public boolean deleteProductById(Integer productId);

}