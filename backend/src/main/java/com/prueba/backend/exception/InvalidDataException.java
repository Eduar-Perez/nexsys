package com.prueba.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prueba.backend.util.Constants;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidDataException(String fildName, Integer fildValue) {
		super(generateMessage(fildName, fildValue));

	}

	private static String generateMessage(String fieldName, Integer fieldValue) {
		return String.format(Constants.INVALIDDATAEXCEPTION, fieldName, fieldValue);
	}

}
