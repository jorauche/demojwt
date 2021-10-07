package com.abycuela.magica.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.abycuela.magica.dto.Rol;
import com.abycuela.magica.dto.UsuarioDTO;


public interface UsuarioRepository extends ReactiveCrudRepository<UsuarioDTO, String> {

}
