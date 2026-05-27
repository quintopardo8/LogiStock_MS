package com.example.LogiStock_MS_01.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.LogiStock_MS_01.dto.request.CategoriaRequest;
import com.example.LogiStock_MS_01.dto.response.CategoriaResponse;
import com.example.LogiStock_MS_01.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toEntity (CategoriaRequest categoriaRequest);
    CategoriaResponse toResponse (Categoria categoria);
    List<CategoriaResponse> toResponseList(List<Categoria> categoriaList);
    

}
