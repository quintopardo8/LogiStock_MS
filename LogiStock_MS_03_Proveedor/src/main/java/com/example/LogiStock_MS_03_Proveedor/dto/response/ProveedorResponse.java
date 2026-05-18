package com.example.LogiStock_MS_03_Proveedor.dto.response;

import lombok.Data;

@Data
public class ProveedorResponse {
    private Long id;
    private String rut;
    private String nombre;
    private String contactoTelefono;
    private String contactoEmail;
    private String direccion;
    private String estado;

}
