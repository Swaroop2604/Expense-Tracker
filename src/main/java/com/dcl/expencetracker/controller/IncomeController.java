package com.dcl.expencetracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.expencetracker.entity.Income;
import com.dcl.expencetracker.service.IncomeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/income")
@SecurityRequirement(name = "bearerAuth")
public class IncomeController {
	 private final IncomeService incomeService;

	    public IncomeController(IncomeService incomeService) {
	        this.incomeService = incomeService;
	    }

	    // Create income
	    @PostMapping
	    public ResponseEntity<Income> createIncome(
	            Authentication authentication,
	            @RequestBody Income income) {

	        String email = authentication.getName();

	        Income savedIncome =
	                incomeService.createIncome(email, income);

	        return ResponseEntity.ok(savedIncome);
	    }

	    // Get all income of logged-in user
	    @GetMapping
	    public ResponseEntity<List<Income>> getAllIncome(
	            Authentication authentication) {

	        String email = authentication.getName();

	        List<Income> incomeList =
	                incomeService.getAllIncome(email);

	        return ResponseEntity.ok(incomeList);
	    }

}
