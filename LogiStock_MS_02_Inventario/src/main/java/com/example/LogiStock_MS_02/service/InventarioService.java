package com.example.LogiStock_MS_02.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LogiStock_MS_02.dto.InventarioRequest;
import com.example.LogiStock_MS_02.dto.InventarioResponse;
import com.example.LogiStock_MS_02.exception.InventarioNoEncontradoException;
import com.example.LogiStock_MS_02.exception.StockInsuficienteException;
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
    log.info("Petición de eliminación para registro de inventario ID: {}", id);
    Inventario inventario = inventarioRepository
        .findById(id)
        .orElseThrow(() -> new InventarioNoEncontradoException("No existe registro de inventario con ID: " + id));
    inventarioRepository.delete(inventario);
    log.info("Registro de inventario ID: {} removido con éxito", id);
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

    public InventarioResponse reservarStock(Long productoId, int cantidad) {
        log.info("Intentando reservar {} unidades para el producto ID: {}", cantidad, productoId);
        
        Inventario inventario = inventarioRepository
                .findByProductoId(productoId)
                .orElseThrow(() -> new InventarioNoEncontradoException("No existe registro de inventario para el producto ID: " + productoId));

        if (inventario.getCantidadDisponible() < cantidad) {
            log.error("Quiebre de stock detectado para producto ID: {}. Solicitado: {}, Disponible: {}", productoId, cantidad, inventario.getCantidadDisponible());
            throw new StockInsuficienteException("Stock insuficiente para realizar la reserva. Disponible: " + inventario.getCantidadDisponible());
        }

        inventario.setCantidadDisponible(inventario.getCantidadDisponible() - cantidad);
        inventario.setCantidadReservada(inventario.getCantidadReservada() + cantidad);

        if (inventario.getCantidadDisponible() <= inventario.getStockMinimo()) {
            log.warn("El producto ID: {} ha alcanzado o caído bajo el stock mínimo ({} unidades restantes)", productoId, inventario.getCantidadDisponible());
        }

        return inventarioMapper.toResponse(inventarioRepository.save(inventario));
    }

    
    public InventarioResponse incrementarStock(Long productoId, int cantidad) {
        log.info("Incrementando inventario físico por recepción. Producto ID: {}, Cantidad: {}", productoId, cantidad);
        
        // Si el inventario no existe para ese producto, se inicializa automáticamente en lugar de fallar
        Inventario inventario = inventarioRepository.findByProductoId(productoId)
                .orElseGet(() -> {
                    log.info("No se encontró registro previo de inventario para producto ID: {}. Creando registro base.", productoId);
                    Inventario nuevoInventario = new Inventario();
                    nuevoInventario.setProductoId(productoId);
                    nuevoInventario.setCantidadDisponible(0);
                    nuevoInventario.setCantidadReservada(0);
                    nuevoInventario.setStockMinimo(5);
                    nuevoInventario.setUbicacion("BODEGA");
                    return nuevoInventario;
                });

        inventario.setCantidadDisponible(inventario.getCantidadDisponible() + cantidad);
        return inventarioMapper.toResponse(inventarioRepository.save(inventario));
    }
    

}
