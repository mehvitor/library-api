package com.example.libraryapi.controller.mappers;

import org.mapstruct.Mapper;

import com.example.libraryapi.controller.dto.UsuarioDTO;
import com.example.libraryapi.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	Usuario toEntity(UsuarioDTO dto);
	
	
	
}
