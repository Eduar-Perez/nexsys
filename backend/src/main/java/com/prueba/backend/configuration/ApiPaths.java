package com.prueba.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ApiPaths {

	@Value("${app.api.path.product}")
	private String pathProduct;

	@Value("${app.api.path.category}")
	private String pathCategory;

}
