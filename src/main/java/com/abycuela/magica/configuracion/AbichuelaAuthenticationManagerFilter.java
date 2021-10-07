package com.abycuela.magica.configuracion;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.abycuela.magica.dto.UsuarioDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbichuelaAuthenticationManagerFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	public AbichuelaAuthenticationManagerFilter( AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.info("Username : {} password {}", username, password); 
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
//		super.successfulAuthentication(request, response, chain, authResult);
		User user =(User)authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("Abichuela".getBytes());
		String access_token = JWT.create()
				.withSubject(user.getUsername()) // algo de informacion del usuario
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // teimpo de duracion del token de un minuto
				.withIssuer(request.getRequestURI().toString())//la url de la aplicacion
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList())) // reglas
				.sign(algorithm);
		
		String refresh_token = JWT.create()
				.withSubject(user.getUsername()) // algo de informacion del usuario
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // teimpo de duracion del token de un minuto
				.withIssuer(request.getRequestURI().toString())//la url de la aplicacion
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList())) // reglas
				.sign(algorithm);
		
		response.setHeader("access_Token", access_token);
		response.setHeader("refresh_token", refresh_token);
		
	}
	
	
}
