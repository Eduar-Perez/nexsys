package com.prueba.backend.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prueba.backend.service.impl.UserDetailsTokenServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final UserDetailsTokenServiceImpl userDetailsService;

	/**
	 * Realiza el filtrado de las solicitudes HTTP para autenticar al usuario utilizando un token JWT.
	 *
	 * @param req la solicitud HTTP que contiene el token JWT en el encabezado de autorización.
	 * @param res la respuesta HTTP que se enviará al cliente.
	 * @param filterChain la cadena de filtros que permite continuar con la ejecución del filtro.
	 * @throws ServletException si se produce un error durante la manipulación de la solicitud o respuesta.
	 * @throws IOException si se produce un error de entrada/salida al procesar la solicitud o respuesta.
	 */
	@Value("${app.api.path.login}")
	private String path; 
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

		String token = jwtProvider.getJwtFromRequest(req);
		if (token != null && jwtProvider.validateToken(token)) {
			String nombreUsuario = jwtProvider.getNameUserFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		} else if (req.getServletPath().equals(path)) {
			filterChain.doFilter(req, res);
			return;
		}

		filterChain.doFilter(req, res);
	}

}
