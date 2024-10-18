package com.prueba.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductNexsysDto {

	private Integer pid;
	private String name;
	private Integer priceFinal;
	private String description;
	private Integer categoryId;
	private String imageUrl;
}
