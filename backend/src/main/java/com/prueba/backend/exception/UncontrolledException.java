package com.prueba.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prueba.backend.util.Constants;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UncontrolledException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UncontrolledException(String cause) {
		super(generateMessage(cause));
	}

	public static String generateMessage(String cause) {
		return String.format(Constants.INTERNAL_SERVER_ERROR, cause) ;
	}
}
