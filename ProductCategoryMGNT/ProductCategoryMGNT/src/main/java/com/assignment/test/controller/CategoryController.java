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
import org.springframework.web.bind.annotation.RestController;

import com.assignment.test.dto.CategoryDto;
import com.assignment.test.exception.ResourceNotCreated;
import com.assignment.test.exception.ResourceNotFound;
import com.assignment.test.response.ApiResponse;
import com.assignment.test.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDto categoryDto) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto category = this.categoryService.createCategory(categoryDto);

			apiResponse.setData(category);
			apiResponse.setMessage("category created succesfully");
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

	@PutMapping("/category/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto updateCategory = this.categoryService.updateCategory(categoryId, categoryDto);

			apiResponse.setData(updateCategory);
			apiResponse.setMessage("category updated succesfully");
			apiResponse.setSucces(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (ResourceNotFound resoureNotFound) {

			apiResponse.setMessage("category not updated");
			apiResponse.setSucces(false);
			apiResponse.setError(true);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Integer categoryId) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);
			apiResponse.setData(categoryById);
			apiResponse.setMessage("category retrived succesfully!!");
			apiResponse.setSucces(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (ResourceNotFound resourceNotFound) {
			apiResponse.setMessage("category not found!!");
			apiResponse.setSucces(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/category")
	public ResponseEntity<ApiResponse> getAllCategory() {
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();

		ApiResponse apiResponse = new ApiResponse();

		if (!allCategories.isEmpty()) {
			apiResponse.setData(allCategories);
			apiResponse.setMessage("All Categories retrieved successfully!!");
			apiResponse.setSucces(true);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setMessage("No Categories found");
			apiResponse.setSucces(false);
			apiResponse.setError(true);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/category/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deletecategoryById(@PathVariable Integer categoryId) {

		boolean deleteCategory = this.categoryService.deleteCategory(categoryId);
		ApiResponse apiResponse = new ApiResponse();

		if (deleteCategory == true) {
			apiResponse.setMessage("category delete succesfully!!");
			apiResponse.setSucces(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setMessage("category not delete!!");
			apiResponse.setSucces(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NO_CONTENT);
		}
	}
}
