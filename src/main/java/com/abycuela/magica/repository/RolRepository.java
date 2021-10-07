package com.abycuela.magica.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.abycuela.magica.dto.Rol;

import reactor.core.publisher.Mono;

public interface RolRepository extends ReactiveCrudRepository<Rol, Long>{

//	Mono<Rol> findByName(String name);
}
