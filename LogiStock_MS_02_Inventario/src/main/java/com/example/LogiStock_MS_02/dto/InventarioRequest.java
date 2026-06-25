package com.example.LogiStock_MS_02.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioRequest {

    @NotNull(message = "El ID del producto es obligatorio.")
    private Long productoId;

    @Min(value = 0, message = "La cantidad debe ser mayor a cero.")
    private int cantidadDisponible;

    @Min(value = 0, message = "La cantidad debe ser mayor a cero.")
    private int cantidadReservada;

    @Min(value = 0, message = "Stock minimo debe ser mayor a cero.")
    private int stockMinimo;

    @NotBlank(message = "Debe incluir ubicación del inventario")
    @Size(max = 100, message = "No puede superar los 100 caracteres.")
    private String ubicacion;


}
