package com.example.libraryapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.libraryapi.model.Usuario;
import com.example.libraryapi.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	 private final UsuarioRepository repository;
	 private final PasswordEncoder encoder;
	
	 public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
		super();
		this.repository = repository;
		this.encoder = encoder;
	}
	
	 public void salvar(Usuario usuario) {
		 var senha = usuario.getSenha();
		 usuario.setSenha(encoder.encode(senha));
		 repository.save(usuario);
	 }
	 
	 public Usuario obterPorLogin(String login) {
		 return repository.findByLogin(login);
	 }
	 
}
