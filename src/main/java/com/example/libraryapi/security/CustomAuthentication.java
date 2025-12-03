package com.example.libraryapi.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.libraryapi.model.Usuario;

import lombok.Getter;

@Getter  
public class CustomAuthentication implements Authentication {

	private final Usuario usuario;
	
	public CustomAuthentication(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public String getName() {
		return usuario.getLogin();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.usuario
				.getRoles()
				.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()); 
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		return usuario;
	}

	@Override
	public Object getPrincipal() {
		return usuario;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	
}
