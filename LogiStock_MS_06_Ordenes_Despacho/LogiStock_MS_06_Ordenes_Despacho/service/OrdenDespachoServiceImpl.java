package com.example.LogiStock_MS_06_Ordenes_Despacho.service;

import com.example.LogiStock_MS_06_Ordenes_Despacho.client.ClienteClient;
import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.externa.Cliente;
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
import java.util.stream.Collectors;

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
        
        Cliente cliente;
        try {
            cliente = clienteClient.obtenerClientePorId(request.getClienteId());
            if (cliente == null) {
                throw new ResourceNotFoundException("El cliente no existe en el sistema remoto.");
            }
            log.info("Validación exitosa con MS Clientes.");
        } catch (Exception e) {
            log.error("Error en validación cruzada: {}", e.getMessage());
            throw new ResourceNotFoundException("No se puede crear la orden. El cliente con ID " 
                    + request.getClienteId() + " no existe o el servicio no está disponible.");
        }

        OrdenDespacho orden = mapper.toEntity(request);
        
        if (orden.getEstado() == null) {
            orden.setEstado(EstadoDespacho.PENDIENTE); 
        }

        OrdenDespacho guardada = repository.save(orden);
        OrdenDespachoResponse response = mapper.toResponse(guardada);
        response.setCliente(cliente); 
        
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenDespachoResponse obtenerPorId(Long id) {
        OrdenDespacho orden = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden de despacho no encontrada con el ID: " + id));

        OrdenDespachoResponse response = mapper.toResponse(orden);
        response.setCliente(buscarClienteSeguro(orden.getClienteId()));
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenDespachoResponse obtenerPorSeguimiento(String numeroSeguimiento) {
        OrdenDespacho orden = repository.findByNumeroSeguimiento(numeroSeguimiento)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con el código de seguimiento: " + numeroSeguimiento));
        
        OrdenDespachoResponse response = mapper.toResponse(orden);
        response.setCliente(buscarClienteSeguro(orden.getClienteId()));
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenDespachoResponse> listarTodas() {
        return repository.findAll().stream()
                .map(orden -> {
                    OrdenDespachoResponse res = mapper.toResponse(orden);
                    res.setCliente(buscarClienteSeguro(orden.getClienteId()));
                    return res;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenDespachoResponse> listarPorEstado(EstadoDespacho estado) {
        return repository.findByEstado(estado).stream()
                .map(orden -> {
                    OrdenDespachoResponse res = mapper.toResponse(orden);
                    res.setCliente(buscarClienteSeguro(orden.getClienteId()));
                    return res;
                })
                .collect(Collectors.toList());
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
        OrdenDespacho guardada = repository.save(orden);
        
        OrdenDespachoResponse response = mapper.toResponse(guardada);
        response.setCliente(buscarClienteSeguro(guardada.getClienteId()));
        return response;
    }

    private Cliente buscarClienteSeguro(Long clienteId) {
        try {
            return clienteClient.obtenerClientePorId(clienteId);
        } catch (Exception e) {
            log.warn("MS Clientes no disponible para ID {}. Cargando fallback.", clienteId);
            Cliente fallback = new Cliente();
            fallback.setId(clienteId);
            fallback.setNombre("Información de cliente no disponible temporalmente");
            return fallback;
        }
    }
}