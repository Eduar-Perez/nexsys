package com.prueba.backend.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.prueba.backend.util.Constants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtEntryPoint implements AuthenticationEntryPoint {

	@Override
	 public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
	     log.error(Constants.ERRORTOKEN);
	     res.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constants.UNAUTHORIZED);
	 }
	
}
