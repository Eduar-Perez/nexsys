package com.prueba.backend.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.prueba.backend.util.Constants;

/**
 * 
 * @author Eduar Perez
 * Clase global para manejo de errores personalizados
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidDataException(InvalidDataException ex) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(), ex.getMessage(), Constants.DATAINVALID, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNotFundException(NotFoundException ex) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(), ex.getMessage(), Constants.REQUESTEMPTY, HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UncontrolledException.class)
	public ResponseEntity<ExceptionResponse> handlerUncontrolledException(UncontrolledException ex) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(), ex.getMessage(), Constants.GENERAL, HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handlerGeneralException(Exception ex) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(), ex.getMessage(), Constants.GENERAL, HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
