package com.abycuela.magica.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abycuela.magica.domain.UsuarioDomain;
import com.abycuela.magica.dto.UsuarioDTO;
import com.abycuela.magica.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class UsuarioDao {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Flux<UsuarioDTO> getAllUser() {
		log.info("Inicia el acceso a datos");
		return  usuarioRepository.findAll();
		
	}
	
	public Mono<UsuarioDTO> getByName(String name){
		log.info("Incia para obtener el nombre");
		return usuarioRepository.findById(name);
	}
	
}
