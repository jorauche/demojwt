package com.abycuela.magica.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abycuela.magica.dto.Rol;
import com.abycuela.magica.dto.UsuarioDTO;
import com.abycuela.magica.repository.RolRepository;
import com.abycuela.magica.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {
	private static final int DELAY_PER_ITEM_MS = 100;
//	private final PasswordEncoder passwordEncoder ;

//	@Autowired UsuarioDao uDao;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	RolRepository rolRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Mono<UsuarioDTO> user = usuarioRepository.findById(username);
		UsuarioDTO userO = user.block();
		if(userO != null) {
			log.error("usuario no encontrado en la base de datos");
			throw  new UsernameNotFoundException("usuario no encontrado en la base de datos\"");
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		userO.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getDescripcion()));
		});
		return new org.springframework.security.core.userdetails.User(userO.getNombre(), userO.getClaveUsuario(), authorities);
	}
	
	public Flux<UsuarioDTO> getusers() {
		log.info("Inicia service");
//		Flux<UsuarioDTO> lstUsuario= uDao.getAllUser().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
		Flux<UsuarioDTO> lstUsuario= usuarioRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));

		return lstUsuario;
	}
	
	public Mono<UsuarioDTO> getUserName(String name) {
		log.info("Regresa un usaurio ");
//		return uDao.getByName(name);
		return usuarioRepository.findById(name);
	}
	
	public void saveUser(UsuarioDTO user){
		log.info("Guardando Usuario");
//		user.setClaveUsuario(passwordEncoder.encode(user.getClaveUsuario()));
		usuarioRepository.save(user);
	}
	
	//falta terminar
	public void addRoleToUser(String username, String roleName) {
		Mono<UsuarioDTO> user = usuarioRepository.findById(username);
//		Mono<Rol> role = rolRepository.findByName(roleName);
		
	}
	public void saveRol(Rol rol) {
		log.info("Alta de un nuevo Rol");
		rolRepository.save(rol);
		
	}


	
}
