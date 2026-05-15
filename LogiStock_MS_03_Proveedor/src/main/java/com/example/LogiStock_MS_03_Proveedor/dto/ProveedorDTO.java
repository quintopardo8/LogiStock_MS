package com.example.LogiStock_MS_03_Proveedor.dto;

public class ProveedorDTO {
    private Long id;
    private String nombre;
    private String contacto;
    private String rubro;

    public ProveedorDTO() {}
    public ProveedorDTO(Long id, String nombre, String contacto, String rubro) {
        this.id = id; this.nombre = nombre; this.contacto = contacto; this.rubro = rubro;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public String getRubro() { return rubro; }
}