package com.example.libraryapi.service;

import org.springframework.stereotype.Service;

import com.example.libraryapi.repository.LivroRepository;

@Service
public class LivroService {

	private final LivroRepository repository;

	public LivroService(LivroRepository repository) {
		super();
		this.repository = repository;
	}

	
}
