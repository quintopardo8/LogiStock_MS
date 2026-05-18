package com.example.LogiStock_MS_03_Proveedor.service;


import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import com.example.LogiStock_MS_03_Proveedor.mapper.ProveedorMapper;
import com.example.LogiStock_MS_03_Proveedor.model.Estado;
import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import com.example.LogiStock_MS_03_Proveedor.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;

    // Inyección de dependencias por constructor (Estándar recomendado)
    public ProveedorServiceImpl(ProveedorRepository repository, ProveedorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) {
        // Validación: No permitir RUT duplicado
        if (repository.findByRut(request.getRut()).isPresent()) {
            throw new RuntimeException("El proveedor con este RUT ya existe");
        }

        Proveedor entidad = mapper.toEntity(request);
        entidad.setEstado(Estado.ACTIVO); // Todo proveedor nuevo inicia activo
        
        Proveedor guardado = repository.save(entidad);
        return mapper.toResponse(guardado);
    }

    @Override
    public List<ProveedorResponse> listarProveedores() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorResponse buscarPorId(Long id) {
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));
        return mapper.toResponse(proveedor);
    }

    @Override
    public ProveedorResponse actualizarProveedor(Long id, ProveedorRequest request) {
        Proveedor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        
        existente.setNombre(request.getNombre());
        existente.setContactoTelefono(request.getContactoTelefono());
        existente.setContactoEmail(request.getContactoEmail());
        existente.setDireccion(request.getDireccion());
        
        Proveedor actualizado = repository.save(existente);
        return mapper.toResponse(actualizado);
    }

    @Override
    public void eliminarProveedor(Long id) {
        Proveedor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        // Borrado lógico (Cambiamos el estado a INACTIVO en vez de borrar el registro físico)
        existente.setEstado(Estado.INACTIVO);
        repository.save(existente);
    }
}