package com.prueba.backend.security;

import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.prueba.backend.util.Constants;
import com.prueba.backend.util.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;

	/**
	 * Metodo encargado de general el token
	 * @param authentication
	 * @return Jwts token generado
	 */
	public String generateToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
		List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		return Jwts.builder()
				.setSubject(usuarioPrincipal.getUsername())
				.claim(Constants.ROLES, roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 180))
				.signWith(getSecret(secret))
				.compact();
	}

	public String getNameUserFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Metodo encargado de vaidar el token
	 * @param token
	 * @return true si es valido o false si no lo es
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			log.error(Constants.TOKENFORMAT);
		} catch (UnsupportedJwtException e) {
			log.error(Constants.TOKENUNSUPORT);
		} catch (ExpiredJwtException e) {
			log.error(Constants.TOKENEXPIRATE);
		} catch (IllegalArgumentException e) {
			log.error(Constants.TOKENEMPTY);
		} catch (SignatureException e) {
			log.error(Constants.FAILALGORITMO);
		}
		return false;
	}

	/**
	 * Metodo encargado de actualizar el token
	 * @param token
	 * @return Jwts actualizado
	 * @throws ParseException
	 */
	public String refreshToken(String token) throws ParseException {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
		} catch (ExpiredJwtException e) {
			JWT jwt = JWTParser.parse(token);
			JWTClaimsSet claims = jwt.getJWTClaimsSet();
			String nombreUsuario = claims.getSubject();
			@SuppressWarnings("unchecked")
			List<String> roles = (List<String>) claims.getClaim(Constants.ROLES);

			return Jwts.builder()
					.setSubject(nombreUsuario)
					.claim(Constants.ROLES, roles)
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + expiration))
					.signWith(getSecret(secret))
					.compact();
		}
		return null;
	}
	/**
	 * Extrae el token JWT del encabezado de autorización de la solicitud HTTP.
	 *
	 * @param request la solicitud HTTP desde la cual se extrae el token.
	 * @return el token JWT si se encuentra en el encabezado de autorización, 
	 *         o null si no se encuentra un token válido.
	 */
	public String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	
	/**
	 * Genera una clave secreta a partir de una cadena de secreto codificada en Base64.
	 * antes de crear una clave HMAC segura para su uso en la firma y validación de tokens JWT.
	 *
	 * @param secret la cadena de secreto codificada en Base64 que se utilizará para crear la clave.
	 * @return la clave generada a partir de la cadena de secreto.
	 * @throws IllegalArgumentException si el secreto proporcionado no es una cadena válida en Base64.
	 */
	private Key getSecret(String secret){
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

}
