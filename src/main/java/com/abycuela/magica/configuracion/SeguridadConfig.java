package com.abycuela.magica.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.abycuela.magica.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SeguridadConfig  extends WebSecurityConfigurerAdapter{
//	@Autowired UsuarioService usuarioService;
	
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };


//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		
//		 http.cors()
//		 .and()
//			 .authorizeRequests()
//			 .antMatchers(HttpMethod.GET,"","")
//			 	.hasAuthority("SCOPE_read")
//			 .antMatchers(HttpMethod.POST,"/cliente/","/login","servidor")
//	         	.hasAuthority("SCOPE_write")
//	         .anyRequest()
//	         .authenticated()
//	         .and()
//	         	.oauth2ResourceServer()
//	         	.jwt();
//		
//	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().anyRequest().permitAll();
		http.addFilter(new AbichuelaAuthenticationManagerFilter(authenticationManagerBean()));
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean(); 
	}
	
	
}
