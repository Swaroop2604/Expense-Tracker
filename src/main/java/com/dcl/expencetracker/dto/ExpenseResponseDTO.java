package com.dcl.expencetracker.dto;

import java.time.LocalDate;

public class ExpenseResponseDTO {

    private Long id;
    private double amount;
    private String description;
    private LocalDate date;

    private Long userId;
    private String userName;

    private Long categoryId;
    private String categoryName;

    public ExpenseResponseDTO() {
    }

    public ExpenseResponseDTO(Long id, double amount, String description,
                              LocalDate date, Long userId, String userName,
                              Long categoryId, String categoryName) {

        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.userId = userId;
        this.userName = userName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
