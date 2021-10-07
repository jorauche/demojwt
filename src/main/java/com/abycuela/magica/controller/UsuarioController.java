package com.abycuela.magica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abycuela.magica.dto.Rol;
import com.abycuela.magica.dto.UsuarioDTO;
import com.abycuela.magica.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UsuarioController {
	@Autowired UsuarioService usuarioService;

	@GetMapping("/obtener/usuarios")
	public Flux<UsuarioDTO> getUser(){
		log.info("Inicia contreller Reativo");
		return usuarioService.getusers();
		
	}
	
	@GetMapping("/api/get/user")
	public Mono<UsuarioDTO> getUserName(String name){
		log.info("Controller getUserName");
		return usuarioService.getUserName(name);
	}
	
	@GetMapping("/api/save/user")
	public void saveUser(UsuarioDTO user){
		log.info("Controller saveUser");
		usuarioService.saveUser(user);
	}
	
	public void saveRol(Rol rol) {
		log.info("Controller saveRol");
		usuarioService.saveRol(rol);
	}
	
	

	
}
