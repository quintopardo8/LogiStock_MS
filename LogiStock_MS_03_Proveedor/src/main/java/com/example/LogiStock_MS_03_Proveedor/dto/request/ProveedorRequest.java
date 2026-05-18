package com.example.LogiStock_MS_03_Proveedor.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProveedorRequest {
    @NotBlank(message = "El RUT es obligatorio")
    @Size(max = 15, message = "El RUT no puede tener más de los 15 caracteres")
    private String rut;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(max = 100, message = "El nombre de la empresa no puede superar tener más de los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El telefono de contacto es obligatorio")
    private String contactoTelefono;
    
    @NotBlank(message = "El email de contacto es obligatorio")
    @Email(message = "El email de contacto debe ser válido")
    private String contactoEmail;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
}
