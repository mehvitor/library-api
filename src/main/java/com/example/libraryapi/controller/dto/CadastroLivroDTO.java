package com.example.libraryapi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import com.example.libraryapi.model.GeneroLivro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CadastroLivroDTO(
		@NotBlank(message = "Campo obrigatório!")
		@ISBN
		String isbn,
		
		@NotBlank(message = "Campo obrigatório!")
		String titulo,
		
		@NotNull(message = "Campo obrigatório!")
		@Past(message = "Não pode ser uma futura da atual")
		LocalDate dataPuplicacao,
		
		GeneroLivro genero,
		BigDecimal preco,
		
		@NotNull(message = "Campo obrigatório!")
		UUID idAutor) {

	
	
}
