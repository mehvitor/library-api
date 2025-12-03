package com.example.libraryapi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.libraryapi.model.Usuario;
import com.example.libraryapi.service.UsuarioService;

@Component
public class SecurityService {

	private final UsuarioService usuarioService;
	
	
	public SecurityService(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	public Usuario obterUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication instanceof CustomAuthentication customAuthentication) {
			return customAuthentication.getUsuario();
		}
		
		return null;
	}
}
