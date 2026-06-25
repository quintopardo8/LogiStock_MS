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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.example.LogiStock_MS_01.dto.request.ActualizarEstadoRequest;
import com.example.LogiStock_MS_01.dto.request.ProductoRequest;
import com.example.LogiStock_MS_01.dto.response.ProductoResponse;
import com.example.LogiStock_MS_01.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    @Operation(summary = "Crear Producto", description = "Registra un nuevo producto en el catálogo de LogiStock")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", description = "Producto creado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponse.class))
        ),
        @ApiResponse(
            responseCode = "400", description = "Datos de entrada inválidos — campos obligatorios ausentes o con formato incorrecto")
    })
    public ResponseEntity<ProductoResponse> crearProducto(@Valid @RequestBody ProductoRequest prodRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardarProducto(prodRequest));
    }

    @Operation(summary = "Listar todos los productos", description = "Retorna el catálogo completo de productos registrados. " + "Si no hay productos, devuelve lista vacía")
    @ApiResponse(responseCode = "200", description = "Catálogo retornado exitosamente")
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    @Operation(summary = "Obtener producto por ID", description = "Recupera un producto específico por su identificador único.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "Producto encontrado",
            content = @Content(schema = @Schema(implementation = ProductoResponse.class))
        ),
        @ApiResponse(
            responseCode = "404", description = "No existe un producto con el ID especificado",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(productoService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequest prodRequest) {
        return ResponseEntity.ok().body(productoService.actualizarProducto(id, prodRequest));
    }

    @Operation(summary = "Eliminar producto", description = "Elimina permanentemente un producto del catálogo. ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Cambiar estado del producto", description = "Modifica el estado de un producto entre ACTIVO e INACTIVO.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<ProductoResponse> actualizarEstado(@PathVariable Long id, @Valid @RequestBody ActualizarEstadoRequest actualizarEstadoRequest) {
    
    ProductoResponse prodResponse = productoService.cambiarEstadoProducto(id, actualizarEstadoRequest);
    return ResponseEntity.ok(prodResponse);
}
}