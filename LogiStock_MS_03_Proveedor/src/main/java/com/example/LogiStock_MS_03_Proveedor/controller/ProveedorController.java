package com.example.LogiStock_MS_03_Proveedor.controller;

import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import com.example.LogiStock_MS_03_Proveedor.service.IProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final IProveedorService service;

    // Inyección del servicio mediante el constructor
    public ProveedorController(IProveedorService service) {
        this.service = service;
    }

    // 1. Crear un nuevo proveedor
    @PostMapping
    public ResponseEntity<ProveedorResponse> crear(@Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse response = service.crearProveedor(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Listar todos los proveedores
    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> listar() {
        List<ProveedorResponse> proveedores = service.listarProveedores();
        return ResponseEntity.ok(proveedores);
    }

    // 3. Buscar un proveedor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> buscarPorId(@PathVariable Long id) {
        ProveedorResponse response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    // 4. Actualizar los datos de un proveedor
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse response = service.actualizarProveedor(id, request);
        return ResponseEntity.ok(response);
    }

    // 5. Eliminar un proveedor (Desactivación lógica)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}