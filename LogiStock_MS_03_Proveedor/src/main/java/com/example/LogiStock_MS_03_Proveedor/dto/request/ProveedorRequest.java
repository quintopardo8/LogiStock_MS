package com.example.LogiStock_MS_03_Proveedor.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequest {

    @NotBlank(message = "El RUT es obligatorio")
    @Size(max = 15, message = "El RUT no puede tener más de 15 caracteres")
    private String rut;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(max = 100, message = "El nombre de la empresa no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono de contacto es obligatorio")
    @Size(max = 20, message = "El teléfono de contacto no puede tener más de 20 caracteres")
    @Pattern(
            regexp = "^[0-9+\\-\\s()]+$",
            message = "El teléfono contiene caracteres inválidos"
    )
    private String contactoTelefono;

    @NotBlank(message = "El email de contacto es obligatorio")
    @Email(message = "El email de contacto debe ser válido")
    @Size(max = 100, message = "El email de contacto no puede tener más de 100 caracteres")
    private String contactoEmail;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    private String direccion;
}