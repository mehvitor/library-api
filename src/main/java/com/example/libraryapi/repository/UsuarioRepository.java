package com.example.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libraryapi.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	Usuario findByLogin(String login);

	Usuario findByEmail(String email);

	
	
}
