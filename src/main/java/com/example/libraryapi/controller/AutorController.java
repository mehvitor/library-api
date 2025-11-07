package com.example.libraryapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.libraryapi.controller.dto.AutorDTO;
import com.example.libraryapi.controller.dto.ErroResposta;
import com.example.libraryapi.controller.mappers.AutorMapper;
import com.example.libraryapi.exception.OperacaoNaoPermitidaException;
import com.example.libraryapi.exception.RegistroDuplicadoException;
import com.example.libraryapi.model.Autor;
import com.example.libraryapi.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

	private AutorService service;
	private AutorMapper mapper; 
	
	public AutorController(AutorService service, AutorMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
	
	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO dto) {
	    try {
			var autor = mapper.toEntity(dto);
			service.salvar(autor);
   
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(autor.getId())
			.toUri();
			
			return ResponseEntity.created(location).build();
		} catch (RegistroDuplicadoException e) {
			var erroDto = ErroResposta.conflito(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
			
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
		var idAutor = UUID.fromString(id);
		
		return service.obterPorId(idAutor)
				.map(autor -> {
					AutorDTO dto = mapper.toDTO(autor);
					return ResponseEntity.ok(dto);
				}).orElseGet(() -> ResponseEntity.notFound().build());
		
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable("id") String id){
		try {
			var idAutor = UUID.fromString(id);
			Optional<Autor> autorOptional = service.obterPorId(idAutor);
			
			if(autorOptional.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			service.deletar(autorOptional.get());
			return ResponseEntity.noContent().build();
		} catch (OperacaoNaoPermitidaException e) {
			var erroRespsota = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroRespsota.status()).body(erroRespsota);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<AutorDTO>> pesquisar(
			@RequestParam(value = "nome", required = false) String nome, 
			@RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
		List<Autor> resultado = service.pesquisaByExample(nome, nacionalidade); 
		List<AutorDTO> lista = resultado
				.stream()
				.map(mapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody AutorDTO dto){
		try {
			var idAutor = UUID.fromString(id);
			Optional<Autor> autorOptional = service.obterPorId(idAutor);
			
			if(autorOptional.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			var autor = autorOptional.get();
			autor.setNome(dto.nome());
			autor.setNacionalidade(dto.nacionalidade());
			autor.setDataNascimentoDate(dto.dataNascimento());
			
			service.atualizar(autor);
			return ResponseEntity.noContent().build();
		
		} catch (RegistroDuplicadoException e) {
			var erroDto = ErroResposta.conflito(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
			
		}
	}
}
