package com.example.libraryapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.AutorRepository;
import com.example.libraryapi.repository.LivroRepository;

@Service
public class TransacaoService {

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Transactional
	public void atualizacaoSemAtualizar() {
		var livro = livroRepository.findById(UUID.fromString("cfbedb29-36e5-4dde-b1fa-660881358a63")).orElse(null);
		livro.setDataPublicacao(LocalDate.of(2024, 1, 6));
		
	}
	
	@Transactional
	public void executar() {
		Autor autor = new Autor();
		autor.setNome("José");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimentoDate(LocalDate.of(1951, 8, 16));
		
		//Unica diferença desse pro cascade é que precisa salvar o Autor antes chamando o repository.
		autorRepository.save(autor);
			
		Livro livro = new Livro();
		livro.setIsbn("53115231-32");
		livro.setPreco(BigDecimal.valueOf(60));
		livro.setGenero(GeneroLivro.FICCAO);
		livro.setTitulo("Teste Livro do José");
		livro.setDataPublicacao(LocalDate.of(1960, 2, 23));
	
		livro.setAutor(autor);
		livroRepository.save(livro);
		
		if(autor.getNome().equals("José")) {
			throw new RuntimeException("Rollback");
		}
	}
	
}
