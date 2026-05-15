package com.example.LogiStock_MS_03_Proveedor.service;

import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import com.example.LogiStock_MS_03_Proveedor.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    public List<Proveedor> listarTodos() {
        return repository.findAll();
    }

    public Proveedor guardar(Proveedor proveedor) {
        return repository.save(proveedor);
    }
}