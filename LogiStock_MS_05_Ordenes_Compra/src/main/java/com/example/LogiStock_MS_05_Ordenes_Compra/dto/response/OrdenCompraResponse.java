package com.example.LogiStock_MS_05_Ordenes_Compra.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.LogiStock_MS_05_Ordenes_Compra.model.EstadoCompra;

import lombok.Data;

@Data
public class OrdenCompraResponse {
    private Long id;
    private Long proveedorId;
    private EstadoCompra estado;
    private LocalDate fechaEmision;
    private LocalDateTime fechaRecepcion;
    private String observaciones;
    private List<DetalleOrdenResponse> detalles; // ListaCompleta PROCESADA
}