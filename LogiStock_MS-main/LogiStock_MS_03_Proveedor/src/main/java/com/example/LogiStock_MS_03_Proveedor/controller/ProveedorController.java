package com.example.LogiStock_MS_03_Proveedor.controller;

import com.example.LogiStock_MS_03_Proveedor.dto.request.ProveedorRequest;
import com.example.LogiStock_MS_03_Proveedor.dto.response.ProveedorResponse;
import com.example.LogiStock_MS_03_Proveedor.service.IProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
@Tag(name = "Módulo Proveedores", description = "Endpoints interactivos para la administración de proveedores en LogiStock")
public class ProveedorController {

    private final IProveedorService service;

    @PostMapping
    @Operation(summary = "Crear un nuevo proveedor", description = "Recibe un JSON con los datos del proveedor, ejecuta las validaciones de negocio y lo registra en el sistema.")
    @ApiResponse(responseCode = "201", description = "Proveedor guardado y registrado exitosamente en la base de datos")
    @ApiResponse(responseCode = "400", description = "Petición incorrecta o parámetros de validación fallidos")
    public ResponseEntity<ProveedorResponse> crear(
            @Valid @RequestBody ProveedorRequest request) {

        ProveedorResponse response = service.crearProveedor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos los proveedores", description = "Retorna una lista completa con todos los proveedores activos registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida con éxito")
    public ResponseEntity<List<ProveedorResponse>> listar() {
        return ResponseEntity.ok(
                service.listarProveedores()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proveedor por ID", description = "Busca en la base de datos un proveedor específico utilizando su ID único.")
    @ApiResponse(responseCode = "200", description = "Proveedor localizado correctamente")
    @ApiResponse(responseCode = "404", description = "El proveedor con el ID solicitado no existe en el sistema")
    public ResponseEntity<ProveedorResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor existente", description = "Modifica los datos de un proveedor basándose en su ID y la información enviada en el cuerpo del JSON.")
    @ApiResponse(responseCode = "200", description = "Proveedor actualizado de forma exitosa")
    @ApiResponse(responseCode = "404", description = "No se puede actualizar debido a que el ID no existe")
    @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos")
    public ResponseEntity<ProveedorResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorRequest request) {

        return ResponseEntity.ok(
                service.actualizarProveedor(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor", description = "Remueve permanentemente un proveedor de la base de datos utilizando su identificador único.")
    @ApiResponse(responseCode = "24", description = "Proveedor eliminado con éxito (No Content)")
    @ApiResponse(responseCode = "404", description = "No se puede eliminar porque el ID del proveedor no fue encontrado")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminarProveedor(id);

        return ResponseEntity.noContent().build();
    }
}