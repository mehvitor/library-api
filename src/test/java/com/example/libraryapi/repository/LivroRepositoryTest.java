package com.example.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;

@SpringBootTest
public class LivroRepositoryTest {

	@Autowired
	LivroRepository repository;
	
	@Autowired
	AutorRepository autorRepository;
	
	
	
	@Test
	public void salvarTest() {
		Livro livro = new Livro();
		livro.setIsbn("90887-84874");
		livro.setPreco(BigDecimal.valueOf(100));
		livro.setGenero(GeneroLivro.FICCAO);
		livro.setTitulo("Game of Thrones");
		livro.setDataPublicacao(LocalDate.of(1999, 4, 23));
		
		Autor autor = autorRepository.findById(UUID.fromString("c4594804-a2d5-436e-a0e2-1b62b4b5ccdb")).orElse(null);
		
		livro.setAutor(autor);
		repository.save(livro);
		
	}		
	 /*
	@Test
	public void salvarCascadeTest() {
		Livro livro = new Livro();
		livro.setIsbn("987731-319");
		livro.setPreco(BigDecimal.valueOf(60));
		livro.setGenero("Fantasia");
		livro.setTitulo("Harry Potter");
		livro.setDataPublicacao(LocalDate.of(2001, 2, 23));
		
		Autor autor = new Autor();
		autor.setNome("J.K Rowling");
		autor.setNacionalidade("Inglesa");
		autor.setDataNascimentoDate(LocalDate.of(1967, 8, 16));
		
		livro.setAutor(autor);
		repository.save(livro);

	}		
	
	@Test
	public void salvarLivroEAutorTest() {
		Livro livro = new Livro();
		livro.setIsbn("98515231-49");
		livro.setPreco(BigDecimal.valueOf(60));
		livro.setGenero("Fantasia");
		livro.setTitulo("Senhor dos Aneis");
		livro.setDataPublicacao(LocalDate.of(1960, 2, 23));
		
		Autor autor = new Autor();
		autor.setNome("Tolken");
		autor.setNacionalidade("Inglesa");
		autor.setDataNascimentoDate(LocalDate.of(105, 8, 16));
	
		//Unica diferença desse pro cascade é que precisa salvar o Autor antes chamando o repository.
		autorRepository.save(autor);
		
		livro.setAutor(autor);
		repository.save(livro);

	}		
	
	@Test
	public void atualizarAutordoLivro() {
		var livroParaAtualizar = repository.findById(UUID.fromString("b344dd43-b762-4556-a96c-3f61d1d23090"))
				.orElse(null);
		var autor = autorRepository.findById(UUID.fromString("d771f931-7ea6-4128-961a-9cf027906274"))
				.orElse(null);
		
		livroParaAtualizar.setAutor(autor);
		repository.save(livroParaAtualizar); 
	}
	
	@Test
	public void deletar() {
		UUID id = UUID.fromString("efe1fb3f-935d-48d6-9750-55349e6b3df0");
		repository.deleteById(id);
	
	}
	
	@Test
	public void buscarLivroTeste() {
		UUID id = UUID.fromString("df0d4deb-2aa5-4381-9d67-822662a14692");
		Livro livro = repository.findById(id).orElse(null);
		
		System.out.println("Livro: ");
		System.out.println(livro.getTitulo());
		System.out.println("Autor: ");
		System.out.println(livro.getAutor().getNome());
	}
	
	
	@Test
	public void pesquisaTituloTeste() {
		List<Livro> lista = repository.findByTitulo("Game of Thrones");
		lista.forEach(System.out::println);
					
	}
	
	@Test
	public void pesquisaIsbmTeste() {
		List<Livro> lista = repository.findByIsbn("98515231-49");
		lista.forEach(System.out::println);
					
	}
	
	@Test
	public void listarLivrosComQueryJPQL() {
		var resultado = repository.listarTodos();
		resultado.forEach(System.out::println);
	}
	
	@Test
	public void listarLivrosComAutorQuery() {
		var resultado = repository.listarAutoresLivros();
		resultado.forEach(System.out::println);
	}

	@Test
	public void updateData() {
		repository.updateDataPubli(LocalDate.of(2000, 1, 1));
	}
	*/
}
