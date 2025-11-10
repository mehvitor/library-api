package com.example.libraryapi.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.LivroRepository;

@Service
public class LivroService {

	private final LivroRepository repository;

	public LivroService(LivroRepository repository) {
		super();
		this.repository = repository;
	}

	public Livro salvar(Livro livro) {
		return repository.save(livro);
		
	}

	public Optional<Livro> obterPorId(UUID id){
		return repository.findById(id);
	}
}