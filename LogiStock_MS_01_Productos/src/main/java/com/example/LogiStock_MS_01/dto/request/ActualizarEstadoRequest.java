package com.example.LogiStock_MS_01.dto.request;

import com.example.LogiStock_MS_01.model.Estado;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarEstadoRequest {


    @NotNull(message = "El campo 'nuevoEstado' es obligatorio y no puede ser nulo.")
    private Estado nuevoEstado;
}
