package com.prueba.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.backend.convert.ProductNexsysConvert;
import com.prueba.backend.dto.ProductNexsysDto;
import com.prueba.backend.entity.ProductNexsysEntity;
import com.prueba.backend.exception.InvalidDataException;
import com.prueba.backend.exception.NotFoundException;
import com.prueba.backend.exception.UncontrolledException;
import com.prueba.backend.repository.IProductNexsysRepository;
import com.prueba.backend.service.IProductNexsysService;
import com.prueba.backend.util.Constants;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class ProductNexsysServiceImpl implements IProductNexsysService{

	private final IProductNexsysRepository productRepository;

	/**
	 * Servicio que consulta todas los productos nexys
	 *
	 * @return productNexsysDto lista de productos
	 * @throws NotFoundException si no se encuentran productos
	 * @throws UncontrolledException si ocurre un error inesperado en la consulta
	 */

	@Override
	public List<ProductNexsysDto> findAllProducts() {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.SERVICE);
		List<ProductNexsysDto> productsNexsysResponse = new ArrayList<>();

		try {
			List<ProductNexsysEntity> productsNexsysEntity = productRepository.findAll();

			if(productsNexsysEntity.isEmpty()) {
				throw new NotFoundException(Constants.NOTFOUNDEXEPTION);
			}

			productsNexsysEntity.forEach(productNexsysEntity -> {
				ProductNexsysDto productNexsysDto = ProductNexsysConvert.convertEntityToDto(productNexsysEntity);
				productsNexsysResponse.add(productNexsysDto);
			});

		} catch (NotFoundException e) {
			log.error(e.getMessage());
			throw e;
		}

		catch (Exception e) {
			throw new UncontrolledException(Constants.INTERNAL_SERVER_ERROR);
		}

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.SERVICE);
		return productsNexsysResponse;
	}

	/**
	 * Crea un producto nexys basado en el Dto proporcionado
	 *
	 * @param productNexsysDto producto a crear
	 * @return ProductNexsysDto representa el id de creación del producto nexys
	 * @throws InvalidDataException si la data es invalida
	 * @throws UncontrolledException si ocurre ocurre un error inesperado al crear el producto nexsys
	 */
	@Override
	public ProductNexsysDto createProductoNexsys(ProductNexsysDto productNexsysDto) {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.SERVICE);

		ProductNexsysDto productResponse = new ProductNexsysDto();

		try {
			if(productNexsysDto.getCategoryId() == null) {
				throw new InvalidDataException(Constants.CATEGORY , productNexsysDto.getCategoryId());
			}

			ProductNexsysEntity productoNexsysSaved = productRepository.save(ProductNexsysConvert.convertDtoToEntity(productNexsysDto)); 
			productResponse.setPid(productoNexsysSaved.getPId());

		} catch (InvalidDataException e) {
			log.error(e.getMessage(), Constants.INVALIDDATAEXCEPTION);
			throw e;
		}

		catch (Exception e) {
			log.error(Constants.INTERNAL_SERVER_ERROR + e.getMessage());
			throw new UncontrolledException(e.getMessage());
		}

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName() + Constants.SERVICE);
		return productResponse;
	}



}
