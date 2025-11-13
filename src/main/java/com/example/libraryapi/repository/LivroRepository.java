package com.example.libraryapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {

	List<Livro> findByAutor(Autor autor);
	
	List<Livro> findByTitulo(String titulo);
	
	Optional<Livro> findByIsbn(String isbn);
	
	@Query(" select l from Livro as l order by l.titulo ")
	List<Livro> listarTodos();
	
	@Query(" select a from Livro l join l.autor a ")
	List<Autor> listarAutoresLivros();
	
	boolean existsByAutor(Autor autor);
	
	@Modifying
	@Transactional
	@Query(" update Livro set dataPublicacao = ?1 ")
	void updateDataPubli(LocalDate novaData);
	
	
 		
}
