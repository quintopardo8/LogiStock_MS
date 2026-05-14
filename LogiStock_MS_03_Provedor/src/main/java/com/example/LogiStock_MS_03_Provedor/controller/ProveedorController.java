package com.example.LogiStock_MS_03_Provedor.controller;

import com.example.LogiStock_MS_03.model.Proveedor;
import com.example.LogiStock_MS_03.service.ProveedorService;
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
        return service.listarTodo();
    }

    @PostMapping
    public Proveedor crear(@RequestBody Proveedor proveedor) {
        return service.guardar(proveedor);
    }
}
