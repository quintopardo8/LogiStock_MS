package com.example.LogiStock_MS_06_Ordenes_Despacho.service;

import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.request.OrdenDespachoRequest;
import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.response.OrdenDespachoResponse;
import com.example.LogiStock_MS_06_Ordenes_Despacho.exception.ResourceNotFoundException;
import com.example.LogiStock_MS_06_Ordenes_Despacho.mapper.OrdenDespachoMapper;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.EstadoDespacho;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.OrdenDespacho;
import com.example.LogiStock_MS_06_Ordenes_Despacho.repository.OrdenDespachoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenDespachoServiceImpl implements IOrdenDespachoService {

    private final OrdenDespachoRepository repository;
    private final OrdenDespachoMapper mapper;

    @Override
    @Transactional
    public OrdenDespachoResponse crearOrden(OrdenDespachoRequest request) {
        OrdenDespacho orden = mapper.toEntity(request);
        OrdenDespacho guardada = repository.save(orden);
        return mapper.toResponse(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenDespachoResponse obtenerPorId(Long id) {
        OrdenDespacho orden = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden de despacho no encontrada con el ID: " + id));
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

        orden.setEstado(nuevoEstado);

        if (nuevoEstado == EstadoDespacho.DESPACHADO) {
            orden.setFechaEnvio(LocalDateTime.now());
        }

        return mapper.toResponse(repository.save(orden));
    }
}