package com.assignment.test;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductCategoryMgntApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCategoryMgntApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper()
	{
		return new ModelMapper();
	}


}
