package com.example.libraryapi.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record AutorDTO(
		UUID id, 
		
		@NotBlank(message = "Campo obrigatório")
		@Size(max = 100, message = "Campo fora do tamanho permitido")
		String nome, 
		
		@NotNull(message = "Campo obrigatório")
		@Past(message = "Não pode ser uma futura da atual")
		LocalDate dataNascimento,
		
		@NotBlank(message = "Campo obrigatório")
		@Size(max = 50, message = "Campo fora do tamanho permitido")
		String nacionalidade) {
	
	
	
}
