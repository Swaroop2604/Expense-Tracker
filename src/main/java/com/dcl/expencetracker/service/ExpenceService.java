package com.dcl.expencetracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dcl.expencetracker.dto.ExpenseRequestDTO;
import com.dcl.expencetracker.dto.ExpenseResponseDTO;
import com.dcl.expencetracker.entity.Category;
import com.dcl.expencetracker.entity.Expence;
import com.dcl.expencetracker.entity.User;
import com.dcl.expencetracker.exception.ResourceNotFoundException;
import com.dcl.expencetracker.mapper.ExpenseMapper;
import com.dcl.expencetracker.repository.CategoryRepository;
import com.dcl.expencetracker.repository.ExpenceRepository;
import com.dcl.expencetracker.repository.UserRepository;
@Service
public class ExpenceService {
	 private final ExpenceRepository expenceRepository;
	 private final UserRepository userRepository;
	 private final CategoryRepository categoryRepository;

	    public ExpenceService(ExpenceRepository expenseRepository ,  UserRepository userRepository,
	            CategoryRepository categoryRepository) {
	        this.expenceRepository = expenseRepository;
	        this.userRepository = userRepository;
	        this.categoryRepository = categoryRepository;
	    }

	    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {

	        User user = userRepository.findById(dto.getUserId())
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "User not found with id: " + dto.getUserId()));

	        Category category = categoryRepository.findById(dto.getCategoryId())
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Category not found with id: " + dto.getCategoryId()));

	        Expence expense = ExpenseMapper.toEntity(dto, user, category);

	        Expence savedExpense = expenceRepository.save(expense);

	        return ExpenseMapper.toDTO(savedExpense);
	    }

	    public List<ExpenseResponseDTO> getAllExpenses() {

	        return expenceRepository.findAll()
	                .stream()
	                .map(ExpenseMapper::toDTO)
	                .toList();
	    }

	    public ExpenseResponseDTO getExpenseById(Long id) {

	        Expence expense = expenceRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Expense not found with id: " + id));

	        return ExpenseMapper.toDTO(expense);
	    }
	    

	    public void deleteExpense(Long id) {
	        Expence expense = expenceRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Expense not found with id: " + id));

	        expenceRepository.delete(expense);
	    }
	    
	    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO dto) {

	        Expence existingExpense = expenceRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Expense not found with id: " + id));

	        User user = userRepository.findById(dto.getUserId())
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "User not found with id: " + dto.getUserId()));

	        Category category = categoryRepository.findById(dto.getCategoryId())
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Category not found with id: " + dto.getCategoryId()));

	        existingExpense.setAmount(dto.getAmount());
	        existingExpense.setDescription(dto.getDescription());
	        existingExpense.setDate(dto.getDate());
	        existingExpense.setUser(user);
	        existingExpense.setCategory(category);

	        Expence updatedExpense = expenceRepository.save(existingExpense);

	        return ExpenseMapper.toDTO(updatedExpense);
	    }
	    
	    public List<ExpenseResponseDTO> getExpensesByUser(Long userId) {

	        List<Expence> expenses = expenceRepository.findByUserId(userId);

	        return expenses.stream()
	                .map(ExpenseMapper::toDTO)
	                .toList();
	    }
	    
	    public List<Expence> getExpensesByCategory(Long categoryId) {
	        return expenceRepository.findByCategoryId(categoryId);
	    }
	    
	    public List<ExpenseResponseDTO> getExpensesByDate(LocalDate date) {

	        List<Expence> expenses = expenceRepository.findByDate(date);

	        return expenses.stream()
	                .map(ExpenseMapper::toDTO)
	                .toList();
	    }
	    
	    public double getTotalExpense() {
	        return expenceRepository.findAll()
	                .stream()
	                .mapToDouble(Expence::getAmount)
	                .sum();
	    }

}
