package com.example.LogiStock_MS_04_Cliente.dto;

public class ClienteDTO {
    private Long id;
    private String nombreCompleto;
    private String email;

    public ClienteDTO() {}
    public ClienteDTO(Long id, String nombreCompleto, String email) {
        this.id = id; this.nombreCompleto = nombreCompleto; this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
