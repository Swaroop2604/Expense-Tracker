package com.dcl.expencetracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.dcl.expencetracker.dto.BudgetStatusDTO;
import com.dcl.expencetracker.entity.Budget;
import com.dcl.expencetracker.entity.User;
import com.dcl.expencetracker.exception.ResourceNotFoundException;
import com.dcl.expencetracker.repository.BudgetRepository;
import com.dcl.expencetracker.repository.ExpenseAnalyticsRepository;
import com.dcl.expencetracker.repository.UserRepository;

@Service
public class BudgetService {

	  private final BudgetRepository budgetRepository;
	    private final UserRepository userRepository;
	    private final ExpenseAnalyticsRepository expenseAnalyticsRepository;

	    public BudgetService(
	            BudgetRepository budgetRepository,
	            UserRepository userRepository,
	            ExpenseAnalyticsRepository expenseAnalyticsRepository) {

	        this.budgetRepository = budgetRepository;
	        this.userRepository = userRepository;
	        this.expenseAnalyticsRepository = expenseAnalyticsRepository;
	    }

	    // Set or update monthly budget
	    public Budget setBudget(
	            String email,
	            BigDecimal amount,
	            int year,
	            int month) {

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                        "User not found with email: " + email
	                    ));

	        Budget budget = budgetRepository
	                .findByUserIdAndYearAndMonth(
	                    user.getId(),
	                    year,
	                    month
	                )
	                .orElse(new Budget());

	        budget.setAmount(amount);
	        budget.setYear(year);
	        budget.setMonth(month);
	        budget.setUser(user);

	        return budgetRepository.save(budget);
	    }

	    // Get monthly budget
	    public Budget getBudget(
	            String email,
	            int year,
	            int month) {

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                        "User not found with email: " + email
	                    ));

	        return budgetRepository
	                .findByUserIdAndYearAndMonth(
	                    user.getId(),
	                    year,
	                    month
	                )
	                .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                        "Budget not found for this month"
	                    ));
	    }

	    // Get budget status
	    public BudgetStatusDTO getBudgetStatus(
	            String email,
	            int year,
	            int month) {

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                        "User not found with email: " + email
	                    ));

	        Budget budget = budgetRepository
	                .findByUserIdAndYearAndMonth(
	                    user.getId(),
	                    year,
	                    month
	                )
	                .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                        "Budget not found for this month"
	                    ));

	        LocalDate startDate = LocalDate.of(year, month, 1);

	        LocalDate endDate = startDate
	                .withDayOfMonth(startDate.lengthOfMonth());

	        Double monthlyExpense =
	                expenseAnalyticsRepository
	                        .getMonthlyExpenseByUserId(
	                                user.getId(),
	                                startDate,
	                                endDate
	                        );

	        BigDecimal spent = BigDecimal.valueOf(monthlyExpense);

	        BigDecimal remaining =
	                budget.getAmount().subtract(spent);

	        String status;

	        if (remaining.compareTo(BigDecimal.ZERO) < 0) {
	            status = "BUDGET EXCEEDED";
	        } else {
	            status = "WITHIN BUDGET";
	        }

	        return new BudgetStatusDTO(
	                budget.getAmount(),
	                spent,
	                remaining,
	                status
	        );
	    }
}
