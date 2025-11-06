package com.example.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.libraryapi.service.TransacaoService;

@SpringBootTest
public class TransacoesTest {

	
	@Autowired
	TransacaoService transacaoService;
	
	@Test
	public void transacaoSimples() {
		transacaoService.executar();
		
	}
	
	@Test
	public void transacaoEstadoManager() {
		transacaoService.atualizacaoSemAtualizar();
	}
	
}
