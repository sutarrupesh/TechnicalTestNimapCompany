package com.assignment.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.test.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
