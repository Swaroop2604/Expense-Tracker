package com.dcl.expencetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcl.expencetracker.dto.CategoryRequestDTO;
import com.dcl.expencetracker.dto.CategoryResponseDTO;
import com.dcl.expencetracker.entity.Category;
import com.dcl.expencetracker.exception.ResourceNotFoundException;
import com.dcl.expencetracker.mapper.CategoryMapper;
import com.dcl.expencetracker.repository.CategoryRepository;
@Service
public class CategoryService {
	 private final CategoryRepository categoryRepository;

	    public CategoryService(CategoryRepository categoryRepository) {
	        this.categoryRepository = categoryRepository;
	    }

	    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {

	        Category category = CategoryMapper.toEntity(dto);

	        Category savedCategory = categoryRepository.save(category);

	        return CategoryMapper.toDTO(savedCategory);
	    }

	    public List<CategoryResponseDTO> getAllCategories() {

	        return categoryRepository.findAll()
	                .stream()
	                .map(CategoryMapper::toDTO)
	                .toList();
	    }

	    public CategoryResponseDTO getCategoryById(Long id) {

	        Category category = categoryRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Category not found with id: " + id));

	        return CategoryMapper.toDTO(category);
	    }

	    public void deleteCategory(Long id) {
	    	Category category = categoryRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Category not found with id: " + id));

	        categoryRepository.delete(category);
	    }
	    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {

	        Category existingCategory = categoryRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Category not found with id: " + id));

	        existingCategory.setName(dto.getName());

	        Category updatedCategory = categoryRepository.save(existingCategory);

	        return CategoryMapper.toDTO(updatedCategory);
	    }
	

}
