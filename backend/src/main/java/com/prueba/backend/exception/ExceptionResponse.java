package com.prueba.backend.exception;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 
 * @author Eduar Perez
 * Clase modelo para modificar la repsuestas de los errores
 *
 */
@Data
public class ExceptionResponse {

	private LocalDateTime fecha;
	private String mensaje;
	private String detalles;
	private Integer codeStatus;

	public ExceptionResponse (LocalDateTime fecha, String mensaje, String detalles, Integer codeStatus) {
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.detalles = detalles;
		this.codeStatus = codeStatus;
	}
}

