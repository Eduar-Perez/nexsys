package com.prueba.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.prueba.backend.service.impl.UserDetailsTokenServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

	/**
	 * Configura y proporciona un bean de AuthenticationManager.
	 *
	 * @param http Configuración de seguridad HTTP.
	 * @param passwordEncoder Codificador de contraseñas para autenticar usuarios.
	 * @param userDetailsServiceImpl Servicio para cargar detalles de usuarios.
	 * @return AuthenticationManager configurado.
	 * @throws Exception Si ocurre un error al crear el AuthenticationManager.
	 */
	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http, PasswordEncoder passwordEncoder, 
			UserDetailsTokenServiceImpl userDetailsServiceImpl) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
		return builder.build();
	}

	/**
	 * Configura la cadena de filtros de seguridad para las solicitudes HTTP.
	 *
	 * @param http Configuración de seguridad HTTP.
	 * @param jwtTokenFilter Filtro para validar tokens JWT.
	 * @param passwordEncoder Codificador de contraseñas para autenticar usuarios.
	 * @param jwtEntryPoint Punto de entrada para manejar errores de autenticación.
	 * @param userDetailsServiceImpl Servicio para cargar detalles de usuarios.
	 * @return La cadena de filtros de seguridad configurada.
	 * @throws Exception Si ocurre un error al configurar la cadena de filtros.
	 */
	
	@Value("${app.api.path.login}")
	private String path;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter, 
			PasswordEncoder passwordEncoder, JwtEntryPoint jwtEntryPoint, 
			UserDetailsTokenServiceImpl userDetailsServiceImpl) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
		http.csrf().disable();
		http.cors();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests().requestMatchers(path, "/prueba/**").permitAll().anyRequest().authenticated();

		http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
