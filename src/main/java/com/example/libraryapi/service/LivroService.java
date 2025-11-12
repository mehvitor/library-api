package com.example.libraryapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.LivroRepository;
import com.example.libraryapi.repository.specs.LivroSpecs;



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
	
	public List<Livro> pesquisa(String isbn, String titulo ,String nomeAutor, GeneroLivro generoLivro, Integer anoPublicacao){
		
		Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(isbn != null) {
			specs = specs.and(LivroSpecs.isbnEqual(isbn));
		}
		
		if(titulo != null) {
			specs = specs.and(LivroSpecs.tituloLike(titulo));
		}
		
		if(titulo != null) {
			specs = specs.and(LivroSpecs.generoEqual(generoLivro));
		}
		
		if(anoPublicacao != null) {
			specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));
		}
		
		if(nomeAutor != null) {
			specs = specs.and(LivroSpecs.nomeAutorLike(nomeAutor));
		}
		
		return repository.findAll(LivroSpecs.isbnEqual(isbn));
		
		
	}
}