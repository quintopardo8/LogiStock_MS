package LogiStock_MS_04_Cliente.dto.response;

import lombok.Data;

@Data
public class ClienteResponse {
    private Long id;
    private String rut;
    private String nombre;
    private String contactoTelefono;
    private String contactoEmail;
    private String direccion;
    private String estado;
}