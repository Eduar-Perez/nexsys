package com.prueba.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.backend.dto.LoginDto;
import com.prueba.backend.security.JwtProvider;
import com.prueba.backend.util.Constants;
import com.prueba.backend.util.JwtResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("${app.api.path.login}")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;

	/**
	 * Controlador para realizar el login
	 * 
	 * @param loginDto
	 * @return JWT token generado
	 */
	
	@PostMapping()
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDto loginDto) {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
					);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtProvider.generateToken(authentication);

			return ResponseEntity.ok(new JwtResponse(token));

		} catch (Exception e) {
	        log.error(Constants.INTERNAL_SERVER_ERROR + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constants.ERRORLOGIN);
		}
	}
}