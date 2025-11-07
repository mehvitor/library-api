package com.example.libraryapi.controller.mappers;

import org.mapstruct.Mapper;

import com.example.libraryapi.controller.dto.AutorDTO;
import com.example.libraryapi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

	Autor toEntity(AutorDTO dto);
	AutorDTO toDTO(Autor autor);
	
	
}
