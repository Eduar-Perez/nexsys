package com.prueba.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.backend.entity.ProductNexsysEntity;

public interface IProductNexsysRepository extends JpaRepository<ProductNexsysEntity, Integer>{
	
}
