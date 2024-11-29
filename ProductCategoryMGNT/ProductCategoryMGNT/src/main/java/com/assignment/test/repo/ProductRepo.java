package com.assignment.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.test.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}
