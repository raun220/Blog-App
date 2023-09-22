package com.raushan.controller;

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

import com.raushan.payloads.ApiResponse;
import com.raushan.payloads.CategoryDto;
import com.raushan.services.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		
		CategoryDto cDto = this.categoryService.createcategory(categoryDto);
		return new ResponseEntity<>(cDto, HttpStatus.CREATED);	
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Deleted successfully", false), HttpStatus.OK);
	}
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId) {
		CategoryDto cDto = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<>(cDto, HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId){
		CategoryDto cDto = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<>(cDto, HttpStatus.OK);
	}
	//Get All
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> cDtos = this.categoryService.getCategories();
		return new ResponseEntity<>(cDtos, HttpStatus.OK);
	}
	

}
