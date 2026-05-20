package com.example.LogiStock_MS_01.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LogiStock_MS_01.dto.request.ActualizarEstadoRequest;
import com.example.LogiStock_MS_01.dto.request.ProductoRequest;
import com.example.LogiStock_MS_01.dto.response.ProductoResponse;
import com.example.LogiStock_MS_01.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(@Valid @RequestBody ProductoRequest prodRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardarProducto(prodRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(productoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequest prodRequest) {
        return ResponseEntity.ok().body(productoService.actualizarProducto(id, prodRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ProductoResponse> actualizarEstado(@PathVariable Long id, @Valid @RequestBody ActualizarEstadoRequest actualizarEstadoRequest) {
    
    ProductoResponse prodResponse = productoService.cambiarEstadoProducto(id, actualizarEstadoRequest);
    return ResponseEntity.ok(prodResponse);
}
}