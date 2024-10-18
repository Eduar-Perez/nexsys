package com.prueba.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsuarioTokenDto {
	private String nombre;
	private String nombreUsuario;
	private String correo;
	private String password;
	private String rolName;

	public UsuarioTokenDto(String nombre, String nombreUsuario, String correo, String password, String rolName) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.password = password;
		this.rolName = rolName;
	}
}