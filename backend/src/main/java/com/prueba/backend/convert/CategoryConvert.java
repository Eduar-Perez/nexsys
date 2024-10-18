package com.prueba.backend.convert;

import com.prueba.backend.dto.CategoryDto;
import com.prueba.backend.entity.CategoryEntity;

public class CategoryConvert {

	CategoryConvert() {}

	public static CategoryDto convertEntityToDtoCategory(CategoryEntity categoryEntity) {

		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCid(categoryEntity.getCId());
		categoryDto.setTitle(categoryEntity.getTitle());

		return categoryDto;
	}

}
