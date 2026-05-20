package com.example.LogiStock_MS_05_Ordenes_Compra.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.OrdenCompraRequest;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.response.OrdenCompraResponse;
import com.example.LogiStock_MS_05_Ordenes_Compra.exception.OrdenNoEncontradaException;
import com.example.LogiStock_MS_05_Ordenes_Compra.exception.OrdenYaRecibidaException;
import com.example.LogiStock_MS_05_Ordenes_Compra.mapper.OrdenCompraMapper;
import com.example.LogiStock_MS_05_Ordenes_Compra.model.DetalleOrden;
import com.example.LogiStock_MS_05_Ordenes_Compra.model.EstadoCompra;
import com.example.LogiStock_MS_05_Ordenes_Compra.model.OrdenCompra;
import com.example.LogiStock_MS_05_Ordenes_Compra.repository.OrdenCompraRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final OrdenCompraMapper ordenCompraMapper;

    public List<OrdenCompraResponse> obtenerTodas() {
        log.info("Consultando todas las órdenes de compra");
        List<OrdenCompra> ordenes = ordenCompraRepository.findAll();
        return ordenCompraMapper.toResponseList(ordenes);
    }

    public OrdenCompraResponse obtenerPorId(Long id) {
        log.info("Buscando orden de compra con ID: {}", id);
        return ordenCompraMapper.toResponse(ordenCompraRepository
                .findById(id)
                .orElseThrow(() -> new OrdenNoEncontradaException(id)));
    }

    public OrdenCompraResponse guardarOrden(OrdenCompraRequest compraRequest) {
        log.info("Creando nueva orden de compra para el proveedor: {}", compraRequest.getProveedorId());
        
        OrdenCompra orden = ordenCompraMapper.toEntity(compraRequest);
        
        //Seteado en service    
        orden.setEstado(EstadoCompra.PENDIENTE);
        orden.setFechaEmision(LocalDate.now());

        // Valido que lista no venga vacia, recorro detalles y los seteo a la orden que estoy creando.
        if (orden.getDetalles() != null) {
            for (DetalleOrden detalle : orden.getDetalles()) {
                detalle.setOrdenCompra(orden);
            }
        }

        return ordenCompraMapper.toResponse(ordenCompraRepository.save(orden));
    }

    public void eliminarOrden(Long id) {
        log.info("Eliminando orden de compra con ID: {}", id);
        ordenCompraRepository.deleteById(id);
    }

    public OrdenCompraResponse actualizarOrden(Long id, OrdenCompraRequest request) {
        log.info("Actualizando orden de compra ID: {}", id);

        OrdenCompra ordenExistente = ordenCompraRepository
                .findById(id)
                .orElseThrow(() -> new OrdenNoEncontradaException(id));

        if (ordenExistente.getEstado() == EstadoCompra.RECIBIDA) {
            throw new OrdenYaRecibidaException(id);
        }

        ordenExistente.setProveedorId(request.getProveedorId());
        ordenExistente.setObservaciones(request.getObservaciones());

        return ordenCompraMapper.toResponse(ordenCompraRepository.save(ordenExistente));
    }

    public OrdenCompraResponse recibirOrdenDeCompra(Long id) {
        log.info("Iniciando recepción de orden de compra ID: {}", id);
        
        OrdenCompra orden = ordenCompraRepository
                .findById(id)
                .orElseThrow(() -> new OrdenNoEncontradaException(id));

        if (orden.getEstado() == EstadoCompra.RECIBIDA) {
            log.warn("La orden de compra ID: {} ya fue procesada y recibida previamente.", id);
            throw new OrdenYaRecibidaException(id);
        }

        if (orden.getDetalles() == null || orden.getDetalles().isEmpty()) {
            log.error("Intento de recibir orden ID: {} sin detalles de productos", id);
            throw new OrdenNoEncontradaException(id);
        }

        orden.setEstado(EstadoCompra.RECIBIDA);
        orden.setFechaRecepcion(java.time.LocalDateTime.now());

        for (DetalleOrden detalle : orden.getDetalles()) {
            detalle.setCantidadRecibida(detalle.getCantidadSolicitada());
            log.info("Producto ID: {} - Registradas {} unidades como recibidas", detalle.getProductoId(), detalle.getCantidadSolicitada());
        }
        
        // Llamada a MS02
        // Por cada detalle de la orden: ms02Client.incrementarStock(detalle.getProductoId(), detalle.getCantidad());
        log.info("Orden de compra ID: {} marcada exitosamente como RECIBIDA.", id);

        return ordenCompraMapper.toResponse(ordenCompraRepository.save(orden));
    }

}
