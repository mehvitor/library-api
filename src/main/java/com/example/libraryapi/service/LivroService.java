package com.example.libraryapi.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.LivroRepository;
import com.example.libraryapi.repository.specs.LivroSpecs;
import com.example.libraryapi.validator.LivroValidator;



@Service
public class LivroService {

	private final LivroRepository repository;
	private final LivroValidator validator;

	public LivroService(LivroRepository repository, LivroValidator validator) {
		super();
		this.repository = repository;
		this.validator = validator;;
	}

	public Livro salvar(Livro livro) {
		validator.validar(livro);
		return repository.save(livro);
		
	}

	public Optional<Livro> obterPorId(UUID id){
		return repository.findById(id);
	}
	
	public void deletar(Livro livro) {
		repository.delete(livro);
	}
	
	public Page<Livro> pesquisa(String isbn, String titulo ,String nomeAutor, GeneroLivro generoLivro, Integer anoPublicacao, Integer pagina, Integer tamanhoPagina){
		
		Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(isbn != null) {
			specs = specs.and(LivroSpecs.isbnEqual(isbn));
		}
		
		if(titulo != null) {
			specs = specs.and(LivroSpecs.tituloLike(titulo));
		}
		
		if(generoLivro != null) {
			specs = specs.and(LivroSpecs.generoEqual(generoLivro));
		}
		
		if(anoPublicacao != null) {
			specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));
		}
		
		if(nomeAutor != null) {
			specs = specs.and(LivroSpecs.nomeAutorLike(nomeAutor));
		}
		
		 Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

	     return repository.findAll(specs, pageRequest);
		
		
	}

	public void atualizar(Livro livro) {
		if(livro.getId() == null) {
			throw new IllegalArgumentException("Para atualizar, é necessário que o livro já esteja salvo!");
		}
		validator.validar(livro);
		repository.save(livro);
	}
}