package com.example.libraryapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.libraryapi.model.GeneroLivro;
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
	
	public void deletar(Livro livro) {
		repository.delete(livro);
	}
	
	public List<Livro> pesquisa(String isbn, String nomeAutor, GeneroLivro generoLivro, Integer anoPublicacao){
		Specification<Livro> specs = null;
		return repository.findAll(specs);
	}
}