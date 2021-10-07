package com.abycuela.magica.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Rol")
public class Rol {
	@Getter
	@Setter
	private Long rol;
	@Getter
	@Setter
	private String descripcion;

}
