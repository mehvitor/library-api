package com.example.libraryapi.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.libraryapi.model.Usuario;
import com.example.libraryapi.service.UsuarioService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UsuarioService usuarioService;
	private final PasswordEncoder encoder;
	
	
	
	public CustomAuthenticationProvider(UsuarioService usuarioService, PasswordEncoder encoder) {
		super();
		this.usuarioService = usuarioService;
		this.encoder = encoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String senhaDigitada = authentication.getCredentials().toString();
		
		Usuario usuarioEncontrado = usuarioService.obterPorLogin(login);
		
		if(usuarioEncontrado == null) {
			throw new UsernameNotFoundException("Usuário ou senha incorreta!");
		}
		
		String senhaCriptografada = usuarioEncontrado.getSenha();
		
		boolean senhasBatem = encoder.matches(senhaDigitada, senhaCriptografada);
		
		if(senhasBatem) {
			return new CustomAuthentication(usuarioEncontrado);
		}
		
		throw new UsernameNotFoundException("Usuário ou senha incorreta!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
