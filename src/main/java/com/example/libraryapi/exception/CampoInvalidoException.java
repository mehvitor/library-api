package com.example.libraryapi.exception;

public class CampoInvalidoException extends RuntimeException {

	private String campo;

	public CampoInvalidoException(String campo, String mensagem) {
		super(mensagem);
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}

	
}
