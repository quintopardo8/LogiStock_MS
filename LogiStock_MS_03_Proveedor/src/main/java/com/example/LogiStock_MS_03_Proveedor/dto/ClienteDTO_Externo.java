package com.example.LogiStock_MS_03_Proveedor.dto;

public class ClienteDTO_Externo {
    private Long id;
    private String nombreCompleto;
    private String email;
    private String telefono; // <--- Agregamos esto para que coincida con el MS_04

    public ClienteDTO_Externo() {}

    public ClienteDTO_Externo(Long id, String nombreCompleto, String email, String telefono) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}