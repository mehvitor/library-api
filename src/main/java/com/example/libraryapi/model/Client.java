package com.example.libraryapi.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@Column(name = "redirect_uri")
	private String redirectURI;
	
	@Column(name = "scope")
	private String scope;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectURI() {
		return redirectURI;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientId=" + clientId + ", clientSecret=" + clientSecret + ", redirectURI="
				+ redirectURI + ", scope=" + scope + "]";
	}
	
	
}
