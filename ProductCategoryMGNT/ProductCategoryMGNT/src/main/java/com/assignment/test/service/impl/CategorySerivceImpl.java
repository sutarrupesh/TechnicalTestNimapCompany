package com.assignment.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.test.dto.CategoryDto;
import com.assignment.test.exception.ResourceNotFound;
import com.assignment.test.model.Category;
import com.assignment.test.repo.CategoryRepo;
import com.assignment.test.service.CategoryService;

@Service
public class CategorySerivceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto, Category.class);

		Category saveCategory = this.categoryRepo.save(category);

		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFound("Category", "CategoryId", categoryId));

		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryTitle(categoryDto.getCategoryTitle());

		Category category1 = this.categoryRepo.save(category);

		return this.modelMapper.map(category1, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFound("Category", "CategoryId", categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> categories = this.categoryRepo.findAll();

		if (categories.isEmpty()) {
			return Collections.emptyList();
		}

		return categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public boolean deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub

		Optional<Category> category = this.categoryRepo.findById(categoryId);

		if (category.isPresent()) {
			this.categoryRepo.deleteById(categoryId);

			return true;
		}

		return false;
	}

}
