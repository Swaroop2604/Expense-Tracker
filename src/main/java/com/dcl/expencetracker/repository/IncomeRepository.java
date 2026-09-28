package com.dcl.expencetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.expencetracker.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
	 List<Income> findByUserId(Long userId);

}
