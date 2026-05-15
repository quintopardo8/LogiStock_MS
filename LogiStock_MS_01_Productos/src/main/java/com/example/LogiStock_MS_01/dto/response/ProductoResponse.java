package com.example.LogiStock_MS_01.dto.response;

import java.util.List;
import com.example.LogiStock_MS_01.model.Estado;
import lombok.Data;

@Data
public class ProductoResponse {
    private Long id;
    private String sku;
    private String nombre;
    private String descripcion;
    private Estado estado;
    private int precio;
    private List<CategoriaResponse> categorias;
}