package com.example.LogiStock_MS_06_Ordenes_Despacho.service;

import com.example.LogiStock_MS_06_Ordenes_Despacho.client.ClienteClient;
import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.request.OrdenDespachoRequest;
import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.response.OrdenDespachoResponse;
import com.example.LogiStock_MS_06_Ordenes_Despacho.exception.ResourceNotFoundException;
import com.example.LogiStock_MS_06_Ordenes_Despacho.mapper.OrdenDespachoMapper;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.EstadoDespacho;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.OrdenDespacho;
import com.example.LogiStock_MS_06_Ordenes_Despacho.repository.OrdenDespachoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdenDespachoServiceImpl implements IOrdenDespachoService {

    private final OrdenDespachoRepository repository;
    private final OrdenDespachoMapper mapper;
    private final ClienteClient clienteClient;

    @Override
    @Transactional
    public OrdenDespachoResponse crearOrden(OrdenDespachoRequest request) {
        log.info("Llamando al MS Clientes para verificar ID: {}", request.getClienteId());
        
        

        OrdenDespacho orden = mapper.toEntity(request);
        
        if (orden.getEstado() == null) {
            orden.setEstado(EstadoDespacho.PENDIENTE); 
        }

        OrdenDespacho guardada = repository.save(orden);
        return mapper.toResponse(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenDespachoResponse obtenerPorId(Long id) {
        OrdenDespacho orden = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden de despacho no encontrada con el ID: " + id));

        try {
            Cliente cliente = clienteClient.obtenerClientePorId(request.getClienteId());
            if (cliente == null) {
                throw new ResourceNotFoundException("El cliente no existe en el sistema remoto.");
            }
            log.info("Validación exitosa con MS Clientes.");
        } catch (Exception e) {
            log.error("Error en validación cruzada: {}", e.getMessage());
            throw new ResourceNotFoundException("No se puede crear la orden. El cliente con ID " 
                    + request.getClienteId() + " no existe o el servicio no está disponible.");
        }

        orden.setCliente(cliente);
        return mapper.toResponse(orden);
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenDespachoResponse obtenerPorSeguimiento(String numeroSeguimiento) {
        OrdenDespacho orden = repository.findByNumeroSeguimiento(numeroSeguimiento)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con el código de seguimiento: " + numeroSeguimiento));
        return mapper.toResponse(orden);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenDespachoResponse> listarTodas() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenDespachoResponse> listarPorEstado(EstadoDespacho estado) {
        return mapper.toResponseList(repository.findByEstado(estado));
    }

    @Override
    @Transactional
    public OrdenDespachoResponse cambiarEstado(Long id, EstadoDespacho nuevoEstado) {
        OrdenDespacho orden = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar. Orden no encontrada con el ID: " + id));

        if (nuevoEstado == EstadoDespacho.DESPACHADO && orden.getEstado() != EstadoDespacho.DESPACHADO) {
            orden.setFechaEnvio(LocalDateTime.now());
        }

        orden.setEstado(nuevoEstado);
        return mapper.toResponse(repository.save(orden));
    }
}