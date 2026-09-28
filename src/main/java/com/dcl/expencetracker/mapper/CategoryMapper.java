package com.dcl.expencetracker.mapper;

import com.dcl.expencetracker.dto.CategoryRequestDTO;
import com.dcl.expencetracker.dto.CategoryResponseDTO;
import com.dcl.expencetracker.entity.Category;

public class CategoryMapper {
	 public static Category toEntity(CategoryRequestDTO dto) {

	        Category category = new Category();

	        category.setName(dto.getName());

	        return category;
	    }

	    public static CategoryResponseDTO toDTO(Category category) {

	        return new CategoryResponseDTO(
	                category.getId(),
	                category.getName()
	        );
	    }

}
