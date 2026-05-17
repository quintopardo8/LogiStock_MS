package com.example.LogiStock_MS_01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LogiStock_MS_01.dto.request.ProductoRequest;
import com.example.LogiStock_MS_01.dto.response.ProductoResponse;
import com.example.LogiStock_MS_01.exception.ProductoNoEncontradoException;
import com.example.LogiStock_MS_01.mapper.ProductoMapper;
import com.example.LogiStock_MS_01.model.Producto;
import com.example.LogiStock_MS_01.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;


    public List<ProductoResponse> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toResponseList(productos);
    }

    public ProductoResponse obtenerPorId(Long id) {
        return  productoMapper.toResponse(productoRepository
                .findById(id)
                .orElseThrow(()-> new ProductoNoEncontradoException(id)));        
    }

    public ProductoResponse guardarProducto(ProductoRequest productoRequest) {
        return productoMapper.toResponse(productoRepository.save(productoMapper.toEntity(productoRequest)));
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public ProductoResponse actualizarProducto(Long id, ProductoRequest productoRequest) {
    Producto productoExistente = productoRepository
        .findById(id)
        .orElseThrow(() -> new ProductoNoEncontradoException(id));


    Producto productoAux = productoMapper.toEntity(productoRequest);
    productoAux.setId(productoExistente.getId());

    return productoMapper.toResponse(productoRepository.save(productoAux));

    }

}    