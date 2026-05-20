package com.example.LogiStock_MS_01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LogiStock_MS_01.dto.request.ActualizarEstadoRequest;
import com.example.LogiStock_MS_01.dto.request.ProductoRequest;
import com.example.LogiStock_MS_01.dto.response.ProductoResponse;
import com.example.LogiStock_MS_01.exception.ProductoNoEncontradoException;
import com.example.LogiStock_MS_01.mapper.ProductoMapper;
import com.example.LogiStock_MS_01.model.Producto;
import com.example.LogiStock_MS_01.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;


    public List<ProductoResponse> obtenerTodosLosProductos() {
        log.info("Consultando catálogo completo de productos");
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toResponseList(productos);
    }

    public ProductoResponse obtenerPorId(Long id) {
        log.info("Se está obteniendo el producto con el id {}",id);
        return  productoMapper.toResponse(productoRepository
                .findById(id)
                .orElseThrow(()-> new ProductoNoEncontradoException(id)));        
    }

    public ProductoResponse guardarProducto(ProductoRequest productoRequest) {
        log.info("Creando nuevo producto en el catálogo. SKU: {}", productoRequest.getSku());
        return productoMapper.toResponse(productoRepository.save(productoMapper.toEntity(productoRequest)));
    }

    public void eliminarProducto(Long id) {
        log.info("Petición de eliminación para producto ID: {}", id);
        Producto producto = productoRepository
            .findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
        productoRepository.delete(producto);
        log.info("Producto con ID: {} eliminado exitosamente", id);
    }

    public ProductoResponse actualizarProducto(Long id, ProductoRequest productoRequest) {
        log.info("Actualizando propiedades del producto ID: {}", id);
        Producto productoExistente = productoRepository
        .findById(id)
        .orElseThrow(() -> new ProductoNoEncontradoException(id));

        productoExistente.setSku(productoRequest.getSku());
        productoExistente.setNombre(productoRequest.getNombre());
        productoExistente.setDescripcion(productoRequest.getDescripcion());
        productoExistente.setPrecio(productoRequest.getPrecio());
        
        return productoMapper.toResponse(productoRepository.save(productoExistente));

    }

    public ProductoResponse cambiarEstadoProducto(Long id, ActualizarEstadoRequest actualizarEstadoRequest) {
        log.info("Cambio de estado para producto ID: {} -> Nuevo Estado: {}", id, actualizarEstadoRequest.getNuevoEstado());
        Producto producto = productoRepository
                .findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
        
        if (producto.getEstado() == actualizarEstadoRequest.getNuevoEstado()) {
            log.warn("El producto ID: {} ya se encontraba en estado {}", id, actualizarEstadoRequest.getNuevoEstado());
        }
        
        producto.setEstado(actualizarEstadoRequest.getNuevoEstado());
        return productoMapper.toResponse(productoRepository.save(producto));
    }

}    