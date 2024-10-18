package com.prueba.backend.convert;

import com.prueba.backend.dto.ProductNexsysDto;
import com.prueba.backend.entity.ProductNexsysEntity;

import lombok.Data;

@Data
public class ProductNexsysConvert {

	ProductNexsysConvert() {}
	
	public static ProductNexsysDto convertEntityToDto(ProductNexsysEntity productNexsysEntity) {

		ProductNexsysDto productoNexsysDto = new ProductNexsysDto();
		productoNexsysDto.setPid(productNexsysEntity.getPId());
		productoNexsysDto.setName(productNexsysEntity.getName());
		productoNexsysDto.setPriceFinal(productNexsysEntity.getPriceFinal());
		productoNexsysDto.setDescription(productNexsysEntity.getDescription());

		return productoNexsysDto;
	}

	public static ProductNexsysEntity convertDtoToEntity(ProductNexsysDto productNexsysDto) {

		ProductNexsysEntity productoNexsysEntity = new ProductNexsysEntity();
		productoNexsysEntity.setPId(productNexsysDto.getPid());
		productoNexsysEntity.setName(productNexsysDto.getName());
		productoNexsysEntity.setPriceFinal(productNexsysDto.getPriceFinal());
		productoNexsysEntity.setDescription(productNexsysDto.getDescription());
		productoNexsysEntity.setImageUrl(productNexsysDto.getImageUrl());
		productoNexsysEntity.setCategoryId(productNexsysDto.getCategoryId());

		return productoNexsysEntity;
	}
	
}
