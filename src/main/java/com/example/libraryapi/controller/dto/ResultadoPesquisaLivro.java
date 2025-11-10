package com.example.libraryapi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.libraryapi.model.GeneroLivro;

public record ResultadoPesquisaLivro(
		UUID id,
		String isbn,
		String titulo,
		LocalDate dataPublicacao,
		GeneroLivro genero,
		BigDecimal preco,
		AutorDTO autor) {

}
