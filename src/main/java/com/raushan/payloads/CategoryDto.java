package com.raushan.payloads;

public class CategoryDto {
	
	private Integer categoryId;
	private String categoryTitle;
	private String categoryDescription;
	
	// No-arg
	public CategoryDto() {
		super();
	}
	
	// getters - setters
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	

	

}
