package com.example.LogiStock_MS_05_Ordenes_Compra.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LogiStock_MS_05_Ordenes_Compra.client.ProductoClient;
import com.example.LogiStock_MS_05_Ordenes_Compra.client.ProveedorClient;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.DetalleOrdenRequest;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.MovimientoStockRequest;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.OrdenCompraRequest;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.response.OrdenCompraResponse;
import com.example.LogiStock_MS_05_Ordenes_Compra.exception.OrdenNoEncontradaException;
import com.example.LogiStock_MS_05_Ordenes_Compra.exception.OrdenYaRecibidaException;
import com.example.LogiStock_MS_05_Ordenes_Compra.exception.ProductoNoEncontradoException;
import com.example.LogiStock_MS_05_Ordenes_Compra.exception.ProveedorNoEncontradoException;
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
    private final com.example.LogiStock_MS_05_Ordenes_Compra.client.InventarioClient inventarioClient;
    private final ProveedorClient proveedorClient;
    private final ProductoClient productoClient;


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
        log.info("Creando una nueva orden de compra para el proveedor ID: {}", compraRequest.getProveedorId());

        try {
            Object proveedor = proveedorClient.obtenerProveedorPorId(compraRequest.getProveedorId());
            if (proveedor == null){
                throw new ProveedorNoEncontradoException(compraRequest.getProveedorId());
            }
            log.info("Proveedor ID: {} validado correctamente.", compraRequest.getProveedorId());

            
        } catch (ProveedorNoEncontradoException e) {
            throw e;
        } catch (Exception e){
            log.error("No se pudo validar el proveedor ID: {}. Error: {}", compraRequest.getProveedorId(), e.getMessage());
        }

        //Valido que exista proveedor

        for(DetalleOrdenRequest detalle : compraRequest.getDetalles()){
            try {
                Object producto = productoClient.obtenerProductoPorId(detalle.getProductoId());
                if (producto == null){
                    throw new ProductoNoEncontradoException(detalle.getProductoId());
                }
                log.info("Producto ID {} validado correctamente",detalle.getProductoId());

            } catch (ProductoNoEncontradoException e) {
                throw e;
            } catch (Exception e){
                log.error("Error validando producto ID: {}. Error: {}", detalle.getProductoId(), e.getMessage());
            }
        }    
        //Valido que existan productos

        OrdenCompra orden = ordenCompraMapper.toEntity(compraRequest);
        orden.setEstado(EstadoCompra.PENDIENTE);
        orden.setFechaEmision(LocalDate.now());

        if (orden.getDetalles() != null && !orden.getDetalles().isEmpty()){
            for(DetalleOrden detalle : orden.getDetalles()){
                detalle.setOrdenCompra(orden);
            }
        }   

        OrdenCompraResponse response = ordenCompraMapper.toResponse(ordenCompraRepository.save(orden));
        log.info("Orden de compra creada con ID: {}", response.getId());
        return response;

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
       log.info("Iniciando recepción de orden de compra ID: {}",id);

       OrdenCompra orden = ordenCompraRepository
            .findById(id)
            .orElseThrow(() -> new OrdenNoEncontradaException(id));
        
        if(orden.getEstado() == EstadoCompra.RECIBIDA){
            log.warn("La orden de compra ID: {} ya fue recibida", id);
            throw new OrdenYaRecibidaException(id);
        } 

        if (orden.getDetalles() == null || orden.getDetalles().isEmpty()) {
            log.error("Orden ID: {} sin detalles de productos", id);
            throw new OrdenNoEncontradaException(id);
        } 
        try{
            for(DetalleOrden detalle : orden.getDetalles()){
                MovimientoStockRequest remotaRequest = new MovimientoStockRequest(detalle.getCantidadSolicitada());

                try {
                    inventarioClient.incrementarStock(detalle.getProductoId(), remotaRequest);
                    detalle.setCantidadRecibida(detalle.getCantidadSolicitada());
                    log.info("Stock incrementado para producto ID: {}", detalle.getProductoId());
                    
                } catch (Exception e) {
                    log.error("Error al incrementar stock de producto ID: {}. Error: {}",
                        detalle.getProductoId(), e.getMessage()
                    );
                }
            }
            orden.setEstado(EstadoCompra.RECIBIDA);
            orden.setFechaRecepcion(LocalDateTime.now());
            ordenCompraRepository.save(orden);
            log.info("Orden de compra ID: {} recibida",id);
        }catch (Exception e){
            log.error("Error en recepcion de orden");
            throw e;
        }

        return ordenCompraMapper.toResponse(orden);

    }

}
