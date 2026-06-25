package com.example.LogiStock_MS_05_Ordenes_Compra.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraRequest {

    @NotNull(message = "El ID del proveedor es obligatorio.")
    private Long proveedorId;

    @NotEmpty(message = "La orden de compra debe incluir al menos un producto en el detalle.")
    private List<DetalleOrdenRequest> detalles;

    private String observaciones;
}
