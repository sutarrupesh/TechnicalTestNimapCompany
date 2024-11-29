package com.assignment.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.test.dto.ProductDto;
import com.assignment.test.exception.ResourceNotCreated;
import com.assignment.test.exception.ResourceNotFound;
import com.assignment.test.response.ApiResponse;
import com.assignment.test.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/category/{categoryId}/product")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto,
			@PathVariable Integer categoryId) {
		ApiResponse apiResponse = new ApiResponse();

		try {

			ProductDto product = this.productService.createProduct(productDto, categoryId);
			apiResponse.setData(product);
			apiResponse.setMessage("product created succesfully");
			apiResponse.setError(false);
			apiResponse.setSucces(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
		}

		catch (ResourceNotCreated ex) {
			apiResponse.setMessage(ex.getMessage());
			apiResponse.setError(true);
			apiResponse.setSucces(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDto productDto,
			@PathVariable Integer productId) {
		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto updateProduct = this.productService.updateProduct(productDto, productId);

			apiResponse.setData(updateProduct);
			apiResponse.setMessage("Product updated succesfully");
			apiResponse.setSucces(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (ResourceNotFound resoureNotFound) {

			apiResponse.setMessage("Product not updated");
			apiResponse.setSucces(false);
			apiResponse.setError(true);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Integer productId) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto productById = this.productService.getProductById(productId);
			apiResponse.setData(productById);
			apiResponse.setMessage("product retrived succesfully!!");
			apiResponse.setSucces(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (ResourceNotFound resourceNotFound) {
			apiResponse.setMessage("product not found!!");
			apiResponse.setSucces(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/product")
	public ResponseEntity<ApiResponse> getAllProduct(
			@RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize) {

		List<ProductDto> allProduct = this.productService.getAllProduct(pageNumber, pageSize);

		ApiResponse apiResponse = new ApiResponse();

		if (!allProduct.isEmpty()) {
			apiResponse.setData(allProduct);
			apiResponse.setMessage("All Product retrieved successfully!!");
			apiResponse.setSucces(true);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setMessage("No Product found");
			apiResponse.setSucces(false);
			apiResponse.setError(true);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/product/delete/{productId}")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Integer productId) {

		boolean deleteProductById = this.productService.deleteProductById(productId);

		ApiResponse apiResponse = new ApiResponse();

		if (deleteProductById == true) {
			apiResponse.setMessage("product delete succesfully!!");
			apiResponse.setSucces(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setMessage("product not delete!!");
			apiResponse.setSucces(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NO_CONTENT);
		}
	}
}
