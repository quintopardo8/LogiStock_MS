package com.example.LogiStock_MS_01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LogiStock_MS_01.dto.request.CategoriaRequest;
import com.example.LogiStock_MS_01.dto.response.CategoriaResponse;
import com.example.LogiStock_MS_01.exception.CategoriaNoEncontradaException;
import com.example.LogiStock_MS_01.mapper.CategoriaMapper;
import com.example.LogiStock_MS_01.model.Categoria;
import com.example.LogiStock_MS_01.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //Para probar la alternativa a declarar logger.
@Service
@RequiredArgsConstructor
public class CategoriaService {

    
    private final CategoriaRepository categoriaRepository;
    
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponse> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categoriaMapper.toResponseList(categorias);
    }

    public CategoriaResponse obtenerPorId(Long id) {
        return  categoriaMapper.toResponse(categoriaRepository
                .findById(id)
                .orElseThrow(()-> new CategoriaNoEncontradaException(id)));        
    }

    public CategoriaResponse guardarCategoria(CategoriaRequest categoriaRequest) {
        log.info("Datos de la categoría al momento de crear {}",categoriaRequest);
        return categoriaMapper.toResponse(categoriaRepository.save(categoriaMapper.toEntity(categoriaRequest)));
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public CategoriaResponse actualizarCategoria(Long id, CategoriaRequest categoriaRequest) {
    Categoria categoriaExistente = categoriaRepository
        .findById(id)
        .orElseThrow(() -> new CategoriaNoEncontradaException(id));

    categoriaExistente.setNombre(categoriaRequest.getNombre());
    categoriaExistente.setDescripcion(categoriaRequest.getDescripcion());

    return categoriaMapper.toResponse(categoriaRepository.save(categoriaExistente));
}
}