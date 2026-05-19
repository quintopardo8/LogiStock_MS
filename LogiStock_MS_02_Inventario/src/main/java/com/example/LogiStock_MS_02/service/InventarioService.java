package com.example.LogiStock_MS_02.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LogiStock_MS_02.dto.InventarioRequest;
import com.example.LogiStock_MS_02.dto.InventarioResponse;
import com.example.LogiStock_MS_02.exception.InventarioNoEncontradoException;
import com.example.LogiStock_MS_02.mapper.InventarioMapper;
import com.example.LogiStock_MS_02.model.Inventario;
import com.example.LogiStock_MS_02.repository.InventarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j //No declaro logger, variable log disponible.
@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InventarioMapper inventarioMapper;

    public List<InventarioResponse> obtenerTodos() {
        List<Inventario> inventarios = inventarioRepository.findAll();
        return inventarioMapper.toResponseList(inventarios);
    }

    public InventarioResponse obtenerPorId(Long id) {
        log.info("Buscando registro de inventario con id {}", id);
        return inventarioMapper.toResponse(inventarioRepository
                .findById(id)
                .orElseThrow(() -> new InventarioNoEncontradoException("No existe registro de inventario con ID: " + id)));
    }

    public InventarioResponse obtenerPorProductoId(Long prodId) {
        log.info("Buscando inventario del producto con id {}", prodId);
        return inventarioMapper.toResponse(inventarioRepository
                .findByProductoId(prodId) //Método creado en repository.
                .orElseThrow(() -> new InventarioNoEncontradoException("No existe inventario para el producto de ID: " + prodId)));
    }

    public InventarioResponse guardarInventario(InventarioRequest inventarioRequest) {
        log.info("Creando registro de inventario para producto ID: {}",inventarioRequest.getProductoId());
        return inventarioMapper.toResponse(inventarioRepository.save(inventarioMapper.toEntity(inventarioRequest)));
    }

    public void eliminarInventario(Long id){
        inventarioRepository.deleteById(id);
    }

    public InventarioResponse actualizarInventario(Long id, InventarioRequest request){
        Inventario inventarioExistente = inventarioRepository
            .findById(id)
            .orElseThrow(()-> new InventarioNoEncontradoException("Inventario no encontrado"));
        
        inventarioExistente.setProductoId(request.getProductoId());
        inventarioExistente.setCantidadDisponible(request.getCantidadDisponible());
        inventarioExistente.setCantidadReservada(request.getCantidadReservada());
        inventarioExistente.setStockMinimo(request.getStockMinimo());
        inventarioExistente.setUbicacion(request.getUbicacion());

        return inventarioMapper.toResponse(inventarioRepository.save(inventarioExistente));
    }

    

}
