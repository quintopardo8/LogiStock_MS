package com.example.LogiStock_MS_01.dto.request;

import java.util.List;

import com.example.LogiStock_MS_01.model.Estado;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoRequest {

    @NotBlank(message = "El SKU es obligatorio")
    @Size(max = 50, message = "El SKU no puede superar los 50 caracteres")
    private String sku;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 200, message = "La descripción no puede superar los 200 caracteres")
    private String descripcion;

    private Estado estado;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private int precio;

    // Recibimos solo los IDs de las categorías para asociarlas en el Service
    private List<Long> categoriasIds;

}
