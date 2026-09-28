package com.dcl.expencetracker.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.expencetracker.dto.BudgetStatusDTO;
import com.dcl.expencetracker.entity.Budget;
import com.dcl.expencetracker.service.BudgetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/budgets")
@SecurityRequirement(name = "bearerAuth")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<Budget> setBudget(
            Authentication authentication,
            @RequestParam BigDecimal amount,
            @RequestParam int year,
            @RequestParam int month) {

        String email = authentication.getName();

        Budget budget = budgetService.setBudget(
                email,
                amount,
                year,
                month
        );

        return ResponseEntity.ok(budget);
    }
    
    @GetMapping
    public ResponseEntity<Budget> getBudget(
            Authentication authentication,
            @RequestParam int year,
            @RequestParam int month) {

        String email = authentication.getName();

        Budget budget = budgetService.getBudget(
                email,
                year,
                month
        );

        return ResponseEntity.ok(budget);
    }
    
    @GetMapping("/status")
    public ResponseEntity<BudgetStatusDTO> getBudgetStatus(
            Authentication authentication,
            @RequestParam int year,
            @RequestParam int month) {

        String email = authentication.getName();

        BudgetStatusDTO budgetStatus =
                budgetService.getBudgetStatus(
                        email,
                        year,
                        month
                );

        return ResponseEntity.ok(budgetStatus);
    }

}
