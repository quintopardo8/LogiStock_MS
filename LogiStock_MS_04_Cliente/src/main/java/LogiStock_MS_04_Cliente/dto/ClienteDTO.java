package LogiStock_MS_04_Cliente.dto;

public class ClienteDTO {
    private Long id;
    private String nombreCompleto;
    private String email;
    private String telefono; 

    public ClienteDTO() {}

    public ClienteDTO(Long id, String nombreCompleto, String email, String telefono) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
    }

    public Long getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}