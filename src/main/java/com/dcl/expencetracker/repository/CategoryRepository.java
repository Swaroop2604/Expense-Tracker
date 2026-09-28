package com.dcl.expencetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.expencetracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
