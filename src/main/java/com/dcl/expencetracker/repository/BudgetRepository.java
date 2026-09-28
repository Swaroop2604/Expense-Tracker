package com.dcl.expencetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.expencetracker.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long>{
	Optional<Budget> findByUserIdAndYearAndMonth(
            Long userId,
            int year,
            int month
    );

}
