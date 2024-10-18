package com.prueba.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.backend.dto.CategoryDto;
import com.prueba.backend.exception.NotFoundException;
import com.prueba.backend.service.ICategoryService;
import com.prueba.backend.util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para categoria
 * @author Windows10Pro
 *
 */

@RestController
@RequestMapping("${app.api.path.category}")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

	private final ICategoryService categoryRepositoryService;
	
	
	@GetMapping()
	public ResponseEntity<List<CategoryDto>> finAllCategory() {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.CONTROLLER);
		List<CategoryDto> response = categoryRepositoryService.findAllCategory();
		
		if(response.isEmpty()) {
			throw new NotFoundException(Constants.NOTFOUNDEXEPTION);
		}
		
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.CONTROLLER);
		return ResponseEntity.ok(response);
	}
	
}
