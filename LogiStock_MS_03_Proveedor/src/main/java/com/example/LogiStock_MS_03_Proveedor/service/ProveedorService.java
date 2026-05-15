package com.example.LogiStock_MS_03_Proveedor.service;

import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import com.example.LogiStock_MS_03_Proveedor.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    @Transactional(readOnly = true)
    public List<Proveedor> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Proveedor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Proveedor guardar(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    @Transactional
    public void eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar: Proveedor no encontrado con ID: " + id);
        }
    }

    @Transactional
    public Optional<Proveedor> actualizar(Long id, Proveedor detalles) {
        return repository.findById(id).map(existente -> {
            existente.setNombreEmpresa(detalles.getNombreEmpresa());
            existente.setContacto(detalles.getContacto());
            existente.setEmail(detalles.getEmail());
            existente.setTelefono(detalles.getTelefono());
            return repository.save(existente);
        });
    }
}