package com.example.LogiStock_MS_03_Proveedor.dto;

public class ClienteDTO_Externo {
    private Long id;
    private String nombreCompleto;
    private String email;


    public ClienteDTO_Externo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
