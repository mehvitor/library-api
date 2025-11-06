package com.example.libraryapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "livro")
@Data
public class Livro {

	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "isbn", length = 20, nullable = false)
	private String isbn;
	
	@Column(name = "titulo", length = 150, nullable = false)
	private String titulo;
	
	@Column(name = "data_publicacao", length = 150, nullable = false)
	private LocalDate dataPublicacao;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

	
	@Column(name = "preco", precision = 18, scale = 2)
	private BigDecimal preco;
	
	@ManyToOne
	//se for utilizar cascade, precisa disso -> (cascade = CascadeType.ALL)
	@JoinColumn(name = "id_autor")
	private Autor autor;

	public Livro() {
		// TODO Auto-generated constructor stub
	}

	public UUID getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public GeneroLivro getGenero() {
		return genero;
	}

	public void setGenero(GeneroLivro genero) {
		this.genero = genero;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", dataPublicacao=" + dataPublicacao
				+ ", genero=" + genero + ", preco=" + preco + ", autor=" + autor + "]";
	}
	
	
	
	
	
}
