package com.prueba.backend.util;

public class Constants {

	Constants() {}
	
	/*
	 * Logs
	 */
	public static final String LOG_IN = "Ingresa a: {}";
	public static final String LOG_OUT = "Sale de: {}";
	public static final String SERVICE = " Service";
	public static final String CONTROLLER = " Controller";
	public static final String ROLES = "roles";
	
	/*
	 * Respuesta Exepciones
	 */
	public static final String NOTFOUNDEXEPTION = "No se econtraron registros en la consulta";
	public static final String INVALIDDATAEXCEPTION = "El campo '%s' tiene un valor inválido: '%s'";
	public static final String DATAINVALID = "Data invalida: ";
	public static final String PRODUCTNOTSAVED = "No fue posible guardar el producto";
	public static final String INTERNAL_SERVER_ERROR = "Error inesperado";
	public static final String CATEGORY = "CategoryId";
	public static final String DATAREQUIRED = "Se requiere la data y el ID es null";
	public static final String REQUESTEMPTY = "La consulta realizada no contiene registros";
	public static final String GENERAL = "Se ha presentado un error no controlado";
	public static final String ERRORLOGIN = "Se ha producido un problema con el Login";
	public static final String ERRORTOKEN = "Se ha producido un problema con el token";
	public static final String UNAUTHORIZED = "No autorizado";
	
	/*
	 * Token
	 */
	public static final String TOKENFORMAT = "token mal formado";
	public static final String TOKENUNSUPORT = "token no soportado";
	public static final String TOKENEXPIRATE = "token expirado";
	public static final String TOKENEMPTY = "token vacío";
	public static final String FAILALGORITMO = "fail en la firma";
}
