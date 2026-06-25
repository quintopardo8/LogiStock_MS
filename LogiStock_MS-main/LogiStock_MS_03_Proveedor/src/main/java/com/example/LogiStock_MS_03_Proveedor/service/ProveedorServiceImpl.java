package com.example.LogiStock_MS_03_Proveedor.service;

import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import com.example.LogiStock_MS_03_Proveedor.exception.ResourceNotFoundException;
import com.example.LogiStock_MS_03_Proveedor.mapper.ProveedorMapper;
import com.example.LogiStock_MS_03_Proveedor.model.Estado;
import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import com.example.LogiStock_MS_03_Proveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProveedorServiceImpl implements IProveedorService {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) {
        log.info("[CREAR PROVEEDOR] Iniciando registro con RUT: {}", request.getRut());

        if (repository.existsByRut(request.getRut())) {
            log.error("[CREAR PROVEEDOR] Error: El RUT {} ya se encuentra registrado", request.getRut());
            throw new IllegalArgumentException("Ya existe un proveedor con el RUT: " + request.getRut());
        }

        if (repository.existsByContactoEmail(request.getContactoEmail())) {
            log.error("[CREAR PROVEEDOR] Error: El Email {} ya se encuentra registrado", request.getContactoEmail());
            throw new IllegalArgumentException("Ya existe un proveedor con el email: " + request.getContactoEmail());
        }

        Proveedor entidad = mapper.toEntity(request);
        entidad.setEstado(Estado.ACTIVO);

        Proveedor guardado = repository.save(entidad);
        log.info("[CREAR PROVEEDOR] Exitoso - Proveedor registrado con ID: {} - Estado: {}", guardado.getId(), guardado.getEstado());

        return mapper.toResponse(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponse> listarProveedores() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorResponse buscarPorId(Long id) {
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("[BUSCAR PROVEEDOR] Error: ID {} no encontrado", id);
                    return new ResourceNotFoundException("Proveedor no encontrado con ID: " + id);
                });

        return mapper.toResponse(proveedor);
    }

    @Override
    public ProveedorResponse actualizarProveedor(Long id, ProveedorRequest request) {
        log.info("[ACTUALIZAR PROVEEDOR] Modificando registro ID: {}", id);

        Proveedor existente = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("[ACTUALIZAR PROVEEDOR] Error: ID {} no existe para actualizar", id);
                    return new ResourceNotFoundException("Proveedor no encontrado con ID: " + id);
                });

        repository.findByRut(request.getRut())
                .ifPresent(proveedor -> {
                    if (!proveedor.getId().equals(id)) {
                        log.error("[ACTUALIZAR PROVEEDOR] Conflicto: El RUT {} pertenece a otro ID: {}", request.getRut(), proveedor.getId());
                        throw new IllegalArgumentException("El RUT ya pertenece a otro proveedor");
                    }
                });

        repository.findByContactoEmail(request.getContactoEmail())
                .ifPresent(proveedor -> {
                    if (!proveedor.getId().equals(id)) {
                        log.error("[ACTUALIZAR PROVEEDOR] Conflicto: El Email {} pertenece a otro ID: {}", request.getContactoEmail(), proveedor.getId());
                        throw new IllegalArgumentException("El email ya pertenece a otro proveedor");
                    }
                });

        mapper.updateEntityFromRequest(request, existente);
        Proveedor actualizado = repository.save(existente);
        
        log.info("[ACTUALIZAR PROVEEDOR] Exitoso - Proveedor ID: {} guardado correctamente", actualizado.getId());

        return mapper.toResponse(actualizado);
    }

    @Override
    public void eliminarProveedor(Long id) {
        log.info("[ELIMINAR PROVEEDOR] Solicitud de borrado lógico para ID: {}", id);

        Proveedor existente = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("[ELIMINAR PROVEEDOR] Error: No se puede eliminar. ID {} no existe", id);
                    return new ResourceNotFoundException("Proveedor no encontrado con ID: " + id);
                });

        existente.setEstado(Estado.INACTIVO);
        repository.save(existente);

        log.warn("[CAMBIO DE ESTADO] Exitoso - El proveedor con ID {} ha sido marcado como INACTIVO en el sistema", id);
    }
}