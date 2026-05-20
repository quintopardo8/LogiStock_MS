package com.example.LogiStock_MS_05_Ordenes_Compra.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenRequest {

    @NotNull(message = "El ID del producto es obligatorio.")
    private Long productoId;

    @Min(value = 1, message = "La cantidad solicitada debe ser al menos 1.")
    private int cantidadSolicitada;

    @Min(value = 0, message = "El precio unitario no puede ser negativo.")
    private int precioUnitario;
}