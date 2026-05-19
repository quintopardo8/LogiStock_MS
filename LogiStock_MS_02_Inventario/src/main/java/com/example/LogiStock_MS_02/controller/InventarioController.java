package com.example.LogiStock_MS_02.controller;

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

import com.example.LogiStock_MS_02.dto.InventarioRequest;
import com.example.LogiStock_MS_02.dto.InventarioResponse;
import com.example.LogiStock_MS_02.service.InventarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponse> crearInventario(@Valid @RequestBody InventarioRequest inventarioRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.guardarInventario(inventarioRequest));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponse>> obtenerTodosLosInventarios() {
        return ResponseEntity.ok().body(inventarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponse> obtenerInventarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(inventarioService.obtenerPorId(id));
    }

    //Endpoint para buscar por PordID
    @GetMapping("/producto/{prodId}")
    public ResponseEntity<InventarioResponse> obtenerInventarioPorProductoId(@PathVariable Long prodId) {
        return ResponseEntity.ok().body(inventarioService.obtenerPorProductoId(prodId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponse> actualizarInventario(@PathVariable Long id, @Valid @RequestBody InventarioRequest requestInventario) {
        return ResponseEntity.ok().body(inventarioService.actualizarInventario(id, requestInventario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}
