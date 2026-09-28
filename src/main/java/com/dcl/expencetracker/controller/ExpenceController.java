package com.dcl.expencetracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.expencetracker.dto.ExpenseRequestDTO;
import com.dcl.expencetracker.dto.ExpenseResponseDTO;
import com.dcl.expencetracker.entity.Expence;
import com.dcl.expencetracker.service.ExpenceService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expences")
@SecurityRequirement(name = "bearerAuth")
public class ExpenceController {
	public final ExpenceService expenceService;
	public ExpenceController(ExpenceService expenceService) {
		this.expenceService=expenceService;
	}
	@PostMapping
	public ExpenseResponseDTO createExpense(
	        @Valid @RequestBody ExpenseRequestDTO expense) {

	    return expenceService.createExpense(expense);
	}
	@GetMapping
	public List<ExpenseResponseDTO> getAllExpenses() {
	    return expenceService.getAllExpenses();
	}
	@GetMapping("/{id}")
	public ExpenseResponseDTO getExpenceById(@PathVariable Long id) {
		return expenceService.getExpenseById(id);
		
	}

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenceService.deleteExpense(id);
    }
    @PutMapping("/{id}")
    public ExpenseResponseDTO updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequestDTO dto) {

        return expenceService.updateExpense(id, dto);
    }
    @GetMapping("/user/{userId}")
    public List<ExpenseResponseDTO> getExpensesByUser(@PathVariable Long userId) {
        return expenceService.getExpensesByUser(userId);
    }
    
    @GetMapping("/category/{categoryId}")
    public List<Expence> getExpensesByCategory(@PathVariable Long categoryId) {
        return expenceService.getExpensesByCategory(categoryId);
    }
    
    @GetMapping("/date/{date}")
    public List<ExpenseResponseDTO> getExpensesByDate(@PathVariable LocalDate date) {
        return expenceService.getExpensesByDate(date);
    }
    
    @GetMapping("/total")
    public double getTotalExpense() {
        return expenceService.getTotalExpense();
    }


}
