package com.example.libraryapi.controller.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
		
		@NotBlank(message = "Campo Obrigatório")
		String login, 
		
		@Email(message = "invalido")  
		@NotBlank(message = "Campo Obrigatório")
		String email, 
		
		@NotBlank(message = "Campo Obrigatório")
		String senha,
		
		List<String> roles) {

}
