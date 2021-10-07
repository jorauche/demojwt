package com.abycuela.magica.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class UsuarioDomain {
	private String nombre;
	private String apellidoMaterno;
	private String apellidoPaterno;
	

}
