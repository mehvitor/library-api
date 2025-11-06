package com.example.libraryapi.repository;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.libraryapi.model.Livro;

@SpringBootTest
public class AutorRepositoryTest {

	@Autowired
	AutorRepository repository;
	
	@Autowired
	LivroRepository livroRepository;
	
	/*@Test
	public void salvarTest() {
		Autor autor = new Autor();
		autor.setNome("Vitoria Thereza");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimentoDate(LocalDate.of(1996, 9, 3));
		
		var autoSave = repository.save(autor);
		System.out.println("Autor salvo: " + autoSave);
	}
	
	
	@Test
	public void atualizarTest() {
		var id = UUID.fromString("1b897e35-c722-4cfe-80cb-9528b41557e8");
		Optional<Autor> possivelAutor = repository.findById(id);
		
		if (possivelAutor.isPresent()) {
			Autor autorEncontrado = possivelAutor.get();
			System.out.println("Dados do Autor: ");
			System.out.println(autorEncontrado);
			
			autorEncontrado.setDataNascimentoDate(LocalDate.of(1960, 1, 31));
			
			repository.save(autorEncontrado);
				
		}	
	}	
	
	@Test
	public void listarTest() {
		List<Autor> lista = repository.findAll();
		lista.forEach(System.out::println);
	}
	
	@Test
	public void deleteById() {
		var id = UUID.fromString("1b897e35-c722-4cfe-80cb-9528b41557e8");
		repository.deleteById(id);
	}
	
	
	
	@Test
	public void salvarAutorComLivrosTest() {
		Autor autor = new Autor();
		autor.setNome("George R. R. Martin");
		autor.setNacionalidade("Americano");
		autor.setDataNascimentoDate(LocalDate.of(1958, 7, 4));
		
		Livro livro = new Livro();
		livro.setIsbn("13120813-9");
		livro.setPreco(BigDecimal.valueOf(85));
		livro.setGenero("Fantasia");
		livro.setTitulo("Fogo & Sangue");
		livro.setDataPublicacao(LocalDate.of(2010, 5, 11));
		livro.setAutor(autor);
		
		Livro livro2 = new Livro();
		livro2.setIsbn("46556813-9");
		livro2.setPreco(BigDecimal.valueOf(75));
		livro2.setGenero("Fantasia");
		livro2.setTitulo("Furia dos Reis");
		livro2.setDataPublicacao(LocalDate.of(2008, 5, 26));
		livro2.setAutor(autor);
		
		Livro livro3 = new Livro();
		livro3.setIsbn("1231233-9");
		livro3.setPreco(BigDecimal.valueOf(70));
		livro3.setGenero("Fantasia");
		livro3.setTitulo("Festim dos Corvos");
		livro3.setDataPublicacao(LocalDate.of(2009, 7, 8));
		livro3.setAutor(autor);
		
		autor.setLivros(new ArrayList<>());
		autor.getLivros().add(livro);
		autor.getLivros().add(livro2);
		autor.getLivros().add(livro3);
		
		repository.save(autor);
		livroRepository.saveAll(autor.getLivros());	
	}
	*/
	
	@Test
	public void listarLivrosTest() {
		var id = UUID.fromString("255b2fe6-fafd-4db8-b842-17b464f21ff7");
		var autor = repository.findById(id).get();
		
		List<Livro> livrosLista = livroRepository.findByAutor(autor);
		autor.setLivros(livrosLista);
		
		autor.getLivros().forEach(System.out::println);
	}
	
	
	
}

