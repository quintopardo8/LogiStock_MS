package com.example.LogiStock_MS_06_Ordenes_Despacho.dto.externa;

import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo; // O 'email', cámbialo según cómo se llame en tu MS_04
    private String telefono;
}