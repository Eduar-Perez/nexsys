package com.prueba.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.backend.entity.CategoryEntity;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer>{
	
}
