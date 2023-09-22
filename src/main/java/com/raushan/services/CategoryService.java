package com.raushan.services;

import java.util.List;

import com.raushan.payloads.CategoryDto;

public interface CategoryService {
	
	// create
	CategoryDto createcategory(CategoryDto categoryDto);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getCategories();

}
