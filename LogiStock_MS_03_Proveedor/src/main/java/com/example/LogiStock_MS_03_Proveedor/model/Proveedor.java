package com.example.LogiStock_MS_03_Proveedor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROVEEDORES")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String contacto;
    private String rubro;
    private String rut;

    // Constructores
    public Proveedor() {}
    public Proveedor(Long id, String nombre, String contacto, String rubro, String rut) {
        this.id = id; this.nombre = nombre; this.contacto = contacto; this.rubro = rubro; this.rut = rut;
    }

    // Getters y Setters Manuales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public String getRubro() { return rubro; }
    public void setRubro(String rubro) { this.rubro = rubro; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
}