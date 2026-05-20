package com.example.LogiStock_MS_05_Ordenes_Compra.controller;

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

import com.example.LogiStock_MS_05_Ordenes_Compra.dto.request.OrdenCompraRequest;
import com.example.LogiStock_MS_05_Ordenes_Compra.dto.response.OrdenCompraResponse;
import com.example.LogiStock_MS_05_Ordenes_Compra.service.OrdenCompraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/ordenes-compra")
@RequiredArgsConstructor
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;

    @PostMapping
    public ResponseEntity<OrdenCompraResponse> crearOrden(@Valid @RequestBody OrdenCompraRequest compraRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenCompraService.guardarOrden(compraRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrdenCompraResponse>> obtenerTodas() {
        return ResponseEntity.ok().body(ordenCompraService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(ordenCompraService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompraResponse> actualizarOrden(@PathVariable Long id, @Valid @RequestBody OrdenCompraRequest request) {
        return ResponseEntity.ok().body(ordenCompraService.actualizarOrden(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenCompraService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }

}
