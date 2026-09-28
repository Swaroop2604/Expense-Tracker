package com.dcl.expencetracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcl.expencetracker.entity.Expence;

public interface ExpenceRepository extends JpaRepository<Expence, Long>{
	List<Expence> findByUserId(Long userId);
	List<Expence> findByCategoryId(Long categoryId);
	
	List<Expence> findByDate(LocalDate date);
	@Query("SELECT SUM(e.amount) FROM Expence e")
	Double getTotalExpenses();

}

