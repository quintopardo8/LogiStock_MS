package com.example.LogiStock_MS_01.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LogiStock_MS_01.dto.request.ProductoRequest;
import com.example.LogiStock_MS_01.dto.response.ProductoResponse;
import com.example.LogiStock_MS_01.exception.CategoriaNoEncontradaException;
import com.example.LogiStock_MS_01.exception.ProductoNoEncontradoException;
import com.example.LogiStock_MS_01.mapper.ProductoMapper;
import com.example.LogiStock_MS_01.model.Producto;
import com.example.LogiStock_MS_01.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public ProductoResponse guardarProducto(ProductoRequest productoRequest) {
        return productoMapper.toResponse(productoRepository.save(productoMapper.toEntity(productoRequest)));
    }

    public List<ProductoResponse> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toResponseList(productos);
    }

    public ProductoResponse obtenerPorId(Long id) {
        return productoMapper.toResponse(productoRepository
            .findById(id)
            .orElseThrow(()-> new ProductoNoEncontradoException(id)));
        
    }

    public ProductoResponse actualizarProducto(Long id, ProductoRequest productoRequest) {
        Producto productoExistente = productoRepository
        .findById(id)
        .orElseThrow(() -> new CategoriaNoEncontradaException(id));
        
    productoExistente.setNombre(productoRequest.getNombre());
    productoExistente.setDescripcion(productoRequest.getDescripcion());

    return productoMapper.toResponse(productoRepository.save(productoExistente));

    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}