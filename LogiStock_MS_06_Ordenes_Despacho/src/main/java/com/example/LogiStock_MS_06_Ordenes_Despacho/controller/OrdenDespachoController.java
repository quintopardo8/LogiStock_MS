package com.example.LogiStock_MS_06_Ordenes_Despacho.controller;

import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.request.OrdenDespachoRequest;
import com.example.LogiStock_MS_06_Ordenes_Despacho.dto.response.OrdenDespachoResponse;
import com.example.LogiStock_MS_06_Ordenes_Despacho.model.EstadoDespacho;
import com.example.LogiStock_MS_06_Ordenes_Despacho.service.IOrdenDespachoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-despacho")
@RequiredArgsConstructor
public class OrdenDespachoController {

    private final IOrdenDespachoService service;

    @PostMapping
    public ResponseEntity<OrdenDespachoResponse> crearOrden(@Valid @RequestBody OrdenDespachoRequest request) {
        return new ResponseEntity<>(service.crearOrden(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDespachoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/seguimiento/{codigo}")
    public ResponseEntity<OrdenDespachoResponse> obtenerPorSeguimiento(@PathVariable String codigo) {
        return ResponseEntity.ok(service.obtenerPorSeguimiento(codigo));
    }

    @GetMapping
    public ResponseEntity<List<OrdenDespachoResponse>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenDespachoResponse>> listarPorEstado(@PathVariable EstadoDespacho estado) {
        return ResponseEntity.ok(service.listarPorEstado(estado));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenDespachoResponse> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoDespacho nuevoEstado) {
        return ResponseEntity.ok(service.cambiarEstado(id, nuevoEstado));
    }
}