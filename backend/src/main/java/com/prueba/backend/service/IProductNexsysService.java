package com.prueba.backend.service;

import java.util.List;

import com.prueba.backend.dto.ProductNexsysDto;

public interface IProductNexsysService {

	public List<ProductNexsysDto> findAllProducts();
	
	public ProductNexsysDto createProductoNexsys(ProductNexsysDto productNexsysDto);
	
	
}
