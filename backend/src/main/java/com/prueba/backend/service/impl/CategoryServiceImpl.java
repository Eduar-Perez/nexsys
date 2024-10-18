package com.prueba.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.backend.convert.CategoryConvert;
import com.prueba.backend.dto.CategoryDto;
import com.prueba.backend.entity.CategoryEntity;
import com.prueba.backend.exception.NotFoundException;
import com.prueba.backend.exception.UncontrolledException;
import com.prueba.backend.repository.ICategoryRepository;
import com.prueba.backend.service.ICategoryService;
import com.prueba.backend.util.Constants;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class CategoryServiceImpl implements ICategoryService{

	private final ICategoryRepository categoryRepository;

	/**
	 * Servicio que consulta todas las categorias
	 * 
	 * @return categoryDto lista con la data de categorias
	 * @throws UncontrolledException si no se encuentran categorias
	 * @throws UncontrolledException si ocurre un error en la consulta
	 *
	 */
	@Override
	public List<CategoryDto> findAllCategory() {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.SERVICE);
		List<CategoryDto> categoryResponse = new ArrayList<>();

		try {
			List<CategoryEntity> categorysEntity = categoryRepository.findAll();

			if(categorysEntity.isEmpty()) {
				throw new NotFoundException(Constants.NOTFOUNDEXEPTION);
			}

			categorysEntity.forEach(categoryEntity -> {
				CategoryDto categoryDto = CategoryConvert.convertEntityToDtoCategory(categoryEntity);
				categoryResponse.add(categoryDto);
			});

		} catch (NotFoundException e) {
			log.error(e.getMessage());
			throw e;
		} 
		catch (Exception e) {
			throw new UncontrolledException(Constants.INTERNAL_SERVER_ERROR);
		}

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.SERVICE);
		return categoryResponse;
	}

}
