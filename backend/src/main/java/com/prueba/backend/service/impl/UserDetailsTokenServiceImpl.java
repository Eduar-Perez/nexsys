package com.prueba.backend.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba.backend.dto.UsuarioTokenDto;
import com.prueba.backend.util.UsuarioPrincipal;

import lombok.Data;

@Service
@Data
public class UserDetailsTokenServiceImpl implements UserDetailsService {
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Carga un usuario por su nombre de usuario.
	 *
	 * @param username el nombre de usuario del usuario a cargar.
	 * @return un objeto UserDetails que representa al usuario cargado.
	 * @throws UsernameNotFoundException si el nombre de usuario no se encuentra en el sistema.
	 */
	@Value("${app.user}")
	private String user;
	@Value("${app.passworduser}")
	private String password;
	@Value("${app.useradmin}")
	private String userAdmin;
	@Value("${app.passwordadmin}")
	private String passwordAdmin;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user".equals(username)) {
			return UsuarioPrincipal.build(new UsuarioTokenDto("User Name", user, "user@example.com", passwordEncoder.encode(password), "ROLE_USER"));
		} else if ("admin".equals(username)) {
			return UsuarioPrincipal.build(new UsuarioTokenDto("Admin Name", userAdmin, "admin@example.com", passwordEncoder.encode(passwordAdmin), "ROLE_ADMIN"));
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		}
	}
}
