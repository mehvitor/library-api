package com.example.libraryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapi.model.Client;
import com.example.libraryapi.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	private final ClientService service;

	public ClientController(ClientService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('GERENTE')")
	public void salvar(@RequestBody Client client){
		service.salvar(client);
	}
}
