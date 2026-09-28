package com.dcl.expencetracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.expencetracker.dto.CategoryRequestDTO;
import com.dcl.expencetracker.dto.CategoryResponseDTO;
import com.dcl.expencetracker.entity.Category;
import com.dcl.expencetracker.service.CategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/catergories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
	 private final CategoryService categoryService;

	    public CategoryController(CategoryService categoryService) {
	        this.categoryService = categoryService;
	    }

	    @PostMapping
	    public CategoryResponseDTO createCategory(
	            @Valid @RequestBody CategoryRequestDTO category) {

	        return categoryService.createCategory(category);
	    }

	    @GetMapping
	    public List<CategoryResponseDTO> getAllCategories() {
	        return categoryService.getAllCategories();
	    }

	    @GetMapping("/{id}")
	    public CategoryResponseDTO getCategoryById(@PathVariable Long id) {
	        return categoryService.getCategoryById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategory(id);
	    }
	    @PutMapping("/{id}")
	    public CategoryResponseDTO updateCategory(
	            @PathVariable Long id,
	            @Valid @RequestBody CategoryRequestDTO dto) {

	        return categoryService.updateCategory(id, dto);
	    }

}
