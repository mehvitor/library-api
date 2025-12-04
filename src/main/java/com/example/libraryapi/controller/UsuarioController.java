package com.example.libraryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapi.controller.dto.UsuarioDTO;
import com.example.libraryapi.controller.mappers.UsuarioMapper;
import com.example.libraryapi.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	private final UsuarioService service;
	private final UsuarioMapper mapper;

	public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid UsuarioDTO dto) {
		var usuario = mapper.toEntity(dto);
		service.salvar(usuario);
	}
}
