package com.example.LogiStock_MS_03_Proveedor.controller;

import com.example.LogiStock_MS_03_Proveedor.dto.ProveedorDTO;
import com.example.LogiStock_MS_03_Proveedor.model.Proveedor;
import com.example.LogiStock_MS_03_Proveedor.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    // Ajustado a los 5 campos del DTO nuevo
    private ProveedorDTO mapiarADto(Proveedor p) {
        return new ProveedorDTO(
                p.getId(),
                p.getNombreEmpresa(),
                p.getContacto(),
                p.getEmail(),
                p.getTelefono()
        );
    }

    // Ajustado para setear los campos reales en la entidad
    private Proveedor mapiarAEntidad(ProveedorDTO dto) {
        Proveedor entidad = new Proveedor();
        entidad.setNombreEmpresa(dto.getNombreEmpresa());
        entidad.setContacto(dto.getContacto());
        entidad.setEmail(dto.getEmail());
        entidad.setTelefono(dto.getTelefono());
        return entidad;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> listar() {
        List<ProveedorDTO> dtos = service.listarTodos()
                .stream()
                .map(this::mapiarADto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> crear(@RequestBody ProveedorDTO dto) {
        Proveedor entidad = mapiarAEntidad(dto);
        Proveedor guardado = service.guardar(entidad);
        return new ResponseEntity<>(mapiarADto(guardado), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(p -> ResponseEntity.ok(mapiarADto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}