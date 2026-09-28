package com.dcl.expencetracker.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dcl.expencetracker.dto.CategoryExpenseDTO;
import com.dcl.expencetracker.entity.Expence;

public interface ExpenseAnalyticsRepository extends JpaRepository<Expence, Long> {
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expence e")
    BigDecimal getTotalExpense();
    
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expence e " +
    	       "WHERE e.user.id = :userId")
    	BigDecimal getTotalExpenseByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expence e " +
    	       "WHERE e.date BETWEEN :startDate AND :endDate")
    	Double getMonthlyExpense(
    	        @Param("startDate") LocalDate startDate,
    	        @Param("endDate") LocalDate endDate);
    
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expence e " +
    	       "WHERE e.user.id = :userId " +
    	       "AND e.date BETWEEN :startDate AND :endDate")
    	Double getMonthlyExpenseByUserId(
    	        @Param("userId") Long userId,
    	        @Param("startDate") LocalDate startDate,
    	        @Param("endDate") LocalDate endDate);
    
    @Query("""
    	       SELECT new com.dcl.expencetracker.dto.CategoryExpenseDTO(
    	           e.category.name,
    	           SUM(e.amount)
    	       )
    	       FROM Expence e
    	       GROUP BY e.category.name
    	       ORDER BY SUM(e.amount) DESC
    	       """)
    	List<CategoryExpenseDTO> getCategoryWiseExpense();
    
    @Query("""
    	       SELECT new com.dcl.expencetracker.dto.CategoryExpenseDTO(
    	           e.category.name,
    	           SUM(e.amount)
    	       )
    	       FROM Expence e
    	       WHERE e.user.id = :userId
    	       GROUP BY e.category.name
    	       ORDER BY SUM(e.amount) DESC
    	       """)
    	List<CategoryExpenseDTO> getCategoryWiseExpenseByUserId(
    	        @Param("userId") Long userId);


}
