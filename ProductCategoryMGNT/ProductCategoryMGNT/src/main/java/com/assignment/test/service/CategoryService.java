package com.assignment.test.service;

import java.util.List;

import com.assignment.test.dto.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto);

	public CategoryDto getCategoryById(Integer categoryId);

	public List<CategoryDto> getAllCategories();

	public boolean deleteCategory(Integer categoryId);
}
