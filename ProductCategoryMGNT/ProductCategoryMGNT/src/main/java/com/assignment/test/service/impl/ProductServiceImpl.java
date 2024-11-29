package com.assignment.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.test.dto.ProductDto;
import com.assignment.test.exception.ResourceNotFound;
import com.assignment.test.model.Category;
import com.assignment.test.model.Product;
import com.assignment.test.repo.CategoryRepo;
import com.assignment.test.repo.ProductRepo;
import com.assignment.test.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto createProduct(ProductDto productDto, Integer categoryId) {
		// TODO Auto-generated method stub

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFound("Category", "category id", categoryId));

		Product product = this.modelMapper.map(productDto, Product.class);
		product.setCategory(category);

		Product product2 = this.productRepo.save(product);

		return this.modelMapper.map(product2, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		// TODO Auto-generated method stub

		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFound("Product", "productId", productId));

		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());

		Product saveProduct = this.productRepo.save(product);
		return this.modelMapper.map(saveProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		// TODO Auto-generated method stub

		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFound("Product", "productId", productId));

		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Product> pageProduct = this.productRepo.findAll(p);

		List<Product> product = pageProduct.getContent();

		if (product.isEmpty()) {
			return Collections.emptyList();
		}

		return product.stream().map((prod) -> this.modelMapper.map(prod, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public boolean deleteProductById(Integer productId) {
		Optional<Product> product = this.productRepo.findById(productId);

		if (product.isPresent()) {
			this.productRepo.deleteById(productId);
			return true;
		}

		return false;
	}

}
