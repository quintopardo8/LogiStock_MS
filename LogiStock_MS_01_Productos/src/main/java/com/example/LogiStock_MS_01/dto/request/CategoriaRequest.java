package com.example.LogiStock_MS_01.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequest {

    @NotBlank(message = "Nombre es obligatorio")
    @Size(min = 1, max = 200, message = "Nombre debe tener entre 1 y 200 caracteres")
    private String nombre;

    @NotBlank(message = "Debe incluir descripcion")
    private String descripcion;

}
