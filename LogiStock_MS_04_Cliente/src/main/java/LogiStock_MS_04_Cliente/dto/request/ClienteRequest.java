package LogiStock_MS_04_Cliente.dto.request;

import LogiStock_MS_04_Cliente.model.Estado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank(message = "El rut es obligatorio")
    @Size(max = 15, message = "El rut no puede tener más de 15 caracteres")
    private String rut;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono es obligatorio")
    private String contactoTelefono;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    private String contactoEmail;

    @NotBlank(message = "La dirección es obligatoria") 
    private String direccion;

    @NotNull(message = "El estado es obligatorio")
    private Estado estado;
}