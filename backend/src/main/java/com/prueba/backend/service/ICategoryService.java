package com.prueba.backend.service;

import java.util.List;

import com.prueba.backend.dto.CategoryDto;

public interface ICategoryService {

	public List<CategoryDto> findAllCategory();
	
}
