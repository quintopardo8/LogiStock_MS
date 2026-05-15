package com.example.LogiStock_MS_03_Proveedor.controller;

import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import com.example.LogiStock_MS_03_Proveedor.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @GetMapping
    public List<Proveedor> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Proveedor guardar(@RequestBody Proveedor proveedor) {
        return service.guardar(proveedor);
    }
}