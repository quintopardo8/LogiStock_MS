package com.example.LogiStock_MS_06_Ordenes_Despacho.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenDespachoRequest {

    @NotBlank(message = "El número de seguimiento es obligatorio.")
    private String numeroSeguimiento;

    @NotNull(message = "El ID del cliente es obligatorio.")
    private Long clienteId;

    @NotBlank(message = "La dirección de despacho no puede estar vacía.")
    private String direccionDespacho;
}