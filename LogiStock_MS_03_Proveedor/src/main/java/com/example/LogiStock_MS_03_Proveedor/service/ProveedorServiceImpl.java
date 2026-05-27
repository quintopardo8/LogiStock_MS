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

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements IProveedorService {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) {
        log.info("Intentando registrar un nuevo proveedor con RUT: {}", request.getRut());
        
        if (repository.findByRut(request.getRut()).isPresent()) {
            log.warn("Operación rechazada: El RUT {} ya existe.", request.getRut());
            throw new RuntimeException("El proveedor con este RUT ya existe");
        }

        Proveedor entidad = mapper.toEntity(request);
        entidad.setEstado(Estado.ACTIVO);
        
        Proveedor guardado = repository.save(entidad);
        log.info("Proveedor guardado exitosamente. ID generado: {}", guardado.getId());
        return mapper.toResponse(guardado);
    }

    @Override
    public List<ProveedorResponse> listarProveedores() {
        log.info("Buscando lista completa de proveedores en la base de datos.");
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public ProveedorResponse buscarPorId(Long id) {
        log.info("Buscando proveedor con ID: {}", id);
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error: Proveedor con ID {} no fue encontrado.", id);
                    return new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id);
                });
        return mapper.toResponse(proveedor);
    }

    @Override
    public ProveedorResponse actualizarProveedor(Long id, ProveedorRequest request) {
        log.info("Actualizando datos del proveedor con ID: {}", id);
        Proveedor existente = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al actualizar: No existe el ID {}", id);
                    return new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id);
                });
        
        mapper.updateEntityFromRequest(request, existente);
        
        Proveedor actualizado = repository.save(existente);
        log.info("Proveedor con ID {} actualizado correctamente.", id);
        return mapper.toResponse(actualizado);
    }

    @Override
    public void eliminarProveedor(Long id) {
        log.info("Ejecutando baja lógica para el proveedor con ID: {}", id);
        Proveedor existente = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al eliminar: No existe el ID {}", id);
                    return new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id);
                });
        
        existente.setEstado(Estado.INACTIVO);
        repository.save(existente);
        log.warn("El proveedor con ID {} ha sido marcado como INACTIVO.", id);
    }
}