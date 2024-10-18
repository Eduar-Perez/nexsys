package com.prueba.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.backend.dto.ProductNexsysDto;
import com.prueba.backend.exception.InvalidDataException;
import com.prueba.backend.exception.NotFoundException;
import com.prueba.backend.service.IProductNexsysService;
import com.prueba.backend.util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para producto Nexsys
 * @author Eduar Perez
 *
 */

@RestController
@RequestMapping("${app.api.path.product}")
@RequiredArgsConstructor
@Slf4j
public class ProductNexsysController {

	private final IProductNexsysService productNexsysService;

	@GetMapping()
	public ResponseEntity<List<ProductNexsysDto>> finAllProductNexsys() {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.CONTROLLER);
		List<ProductNexsysDto> response = productNexsysService.findAllProducts();

		if(response.isEmpty()) {
			throw new NotFoundException(Constants.NOTFOUNDEXEPTION);
		}

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.CONTROLLER);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ProductNexsysDto> createProductNexsys(@Validated @RequestBody ProductNexsysDto productNexysDto) {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.CONTROLLER);

		ProductNexsysDto response = productNexsysService.createProductoNexsys(productNexysDto);

		if (response.getPid() == null) {
			log.info(Constants.PRODUCTNOTSAVED);
			throw new InvalidDataException(Constants.INVALIDDATAEXCEPTION, response.getPid());

		}
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.CONTROLLER);
		return ResponseEntity.ok(response);
	}
}

