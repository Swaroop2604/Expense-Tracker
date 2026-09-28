package com.dcl.expencetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.expencetracker.dto.CategoryExpenseDTO;
import com.dcl.expencetracker.service.ExpenseAnalyticsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/analytics")
public class ExpenseAnalyticsController {
	private final ExpenseAnalyticsService expenseAnalyticsService;

    public ExpenseAnalyticsController(ExpenseAnalyticsService expenseAnalyticsService) {
        this.expenseAnalyticsService = expenseAnalyticsService;
    }

    @GetMapping("/total")
    public BigDecimal getTotalExpense(Authentication authentication) {

        String email = authentication.getName();

        return expenseAnalyticsService
                .getTotalExpenseByUserEmail(email);
    }
    @GetMapping("/monthly")
    public Double getMonthlyExpense(
            Authentication authentication,
            @RequestParam int year,
            @RequestParam int month) {

        String email = authentication.getName();

        return expenseAnalyticsService
                .getMonthlyExpenseByUserEmail(email, year, month);
    }
    
    @GetMapping("/category")
    public List<CategoryExpenseDTO> getCategoryWiseExpense(
            Authentication authentication) {

        String email = authentication.getName();

        return expenseAnalyticsService
                .getCategoryWiseExpenseByUserEmail(email);
    }

}
