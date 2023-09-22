package com.raushan.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raushan.entity.Category;
import com.raushan.exceptions.ResourceNotFoundException;
import com.raushan.payloads.CategoryDto;
import com.raushan.repositories.CategoryRepo;
import com.raushan.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	
	// create
	@Override
	public CategoryDto createcategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addCat, CategoryDto.class);
	}

	//detete
	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		this.categoryRepo.delete(category);
		
		
	}

	//update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		 Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		 category.setCategoryTitle(categoryDto.getCategoryTitle());
		 category.setCategoryDescription(categoryDto.getCategoryDescription());
		 Category updatedCategory = this.categoryRepo.save(category);
		 
		//Category category = this.modelMapper.map(categoryDto, Category.class);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	//get
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	//getAll
	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Category> allCategory = this.categoryRepo.findAll();
		List<CategoryDto> allCategoryDto = allCategory.stream().map(category->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		return allCategoryDto;
	}

}
