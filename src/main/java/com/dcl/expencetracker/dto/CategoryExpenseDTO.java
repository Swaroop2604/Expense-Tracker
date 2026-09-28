package com.dcl.expencetracker.dto;

public class CategoryExpenseDTO {
	private String categoryName;
    private Double totalAmount;

    public CategoryExpenseDTO(String categoryName, Double totalAmount) {
        this.categoryName = categoryName;
        this.totalAmount = totalAmount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }	

}
