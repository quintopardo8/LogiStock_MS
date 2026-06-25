package com.example.LogiStock_MS_05_Ordenes_Compra.dto.response;

import lombok.Data;

@Data
public class DetalleOrdenResponse {
    private Long id;
    private Long productoId;
    private int cantidadSolicitada;
    private int cantidadRecibida; // PARTE EN 0 !
    private int precioUnitario;
}