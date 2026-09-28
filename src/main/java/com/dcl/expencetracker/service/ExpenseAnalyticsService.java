package com.dcl.expencetracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dcl.expencetracker.dto.CategoryExpenseDTO;
import com.dcl.expencetracker.entity.User;
import com.dcl.expencetracker.exception.ResourceNotFoundException;
import com.dcl.expencetracker.repository.ExpenseAnalyticsRepository;
import com.dcl.expencetracker.repository.UserRepository;

@Service
public class ExpenseAnalyticsService {
	private final ExpenseAnalyticsRepository expenseAnalyticsRepository;
	private final UserRepository userRepository;

    public ExpenseAnalyticsService(ExpenseAnalyticsRepository expenseAnalyticsRepository,UserRepository userRepository) {
        this.expenseAnalyticsRepository = expenseAnalyticsRepository;
        this.userRepository = userRepository;
    }

    public BigDecimal getTotalExpense() {
        return expenseAnalyticsRepository.getTotalExpense();
    }
    
    public BigDecimal getTotalExpenseByUserEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with email: " + email
                    ));

        return expenseAnalyticsRepository
                .getTotalExpenseByUserId(user.getId());
    }
    
    public Double getMonthlyExpense(int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);

        LocalDate endDate = startDate.withDayOfMonth(
                startDate.lengthOfMonth()
        );

        return expenseAnalyticsRepository.getMonthlyExpense(
                startDate,
                endDate
        );
    }
    
    public Double getMonthlyExpenseByUserEmail(
            String email, int year, int month) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with email: " + email
                    ));

        LocalDate startDate = LocalDate.of(year, month, 1);

        LocalDate endDate = startDate.withDayOfMonth(
                startDate.lengthOfMonth()
        );

        return expenseAnalyticsRepository.getMonthlyExpenseByUserId(
                user.getId(),
                startDate,
                endDate
        );
    }
    public List<CategoryExpenseDTO> getCategoryWiseExpense() {
        return expenseAnalyticsRepository.getCategoryWiseExpense();
    }
    
    public List<CategoryExpenseDTO> getCategoryWiseExpenseByUserEmail(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with email: " + email
                    ));

        return expenseAnalyticsRepository
                .getCategoryWiseExpenseByUserId(user.getId());
    }
	

}
