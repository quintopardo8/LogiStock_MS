package com.example.LogiStock_MS_03_Proveedor.dto;

public class ProveedorDTO {
    private Long id;
    private String nombreEmpresa;
    private String contacto;
    private String email;
    private String telefono;

    public ProveedorDTO() {}

    public ProveedorDTO(Long id, String nombreEmpresa, String contacto, String email, String telefono) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombreEmpresa() { return nombreEmpresa; }
    public String getContacto() { return contacto; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}