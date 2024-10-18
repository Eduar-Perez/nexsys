package com.prueba.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto_nexsys")
@Data
public class ProductNexsysEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private Integer pId;

	@Column(name = "name")
	private String name;

	@Column(name = "buy_price")
	private Integer priceFinal;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String imageUrl;
	
	@Column(name = "categoty_platzi")
	private Integer categoryId;
}
