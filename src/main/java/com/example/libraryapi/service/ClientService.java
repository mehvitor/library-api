package com.example.libraryapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.libraryapi.model.Client;
import com.example.libraryapi.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository repository;
	private final PasswordEncoder encoder;

	
	public ClientService(ClientRepository repository, PasswordEncoder encoder) {
		super();
		this.repository = repository;
		this.encoder = encoder;
	}

	public Client salvar(Client client) {
		var senhaCriptografada = encoder.encode(client.getClientSecret());
		client.setClientSecret(senhaCriptografada);
		return repository.save(client);
	}
	
	public Client obterPorClientID(String clientId) {
		return repository.findByClientId(clientId);
	}
	
}
