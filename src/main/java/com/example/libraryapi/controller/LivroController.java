package com.example.libraryapi.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapi.controller.dto.CadastroLivroDTO;
import com.example.libraryapi.controller.dto.ResultadoPesquisaLivro;
import com.example.libraryapi.controller.mappers.LivroMapper;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.service.LivroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

	private final LivroService service;
	private final LivroMapper mapper;

	public LivroController(LivroService service, LivroMapper mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto){
		
		Livro livro = mapper.toEntity(dto);	
		service.salvar(livro);
		var url = gerarHeaderLocation(livro.getId());
		return ResponseEntity.created(url).build();
	
}
	
	@GetMapping("{id}")
	public ResponseEntity<ResultadoPesquisaLivro> obterDetalhes(@PathVariable("id") String id){
		return service.obterPorId(UUID.fromString(id))
				.map(livro -> {
					var dto = mapper.toDTO(livro);
					return ResponseEntity.ok(dto);
				}).orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable("id") String id){
		return service.obterPorId(UUID.fromString(id))
				.map(livro -> {
					service.deletar(livro);
					return ResponseEntity.noContent().build();
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
}