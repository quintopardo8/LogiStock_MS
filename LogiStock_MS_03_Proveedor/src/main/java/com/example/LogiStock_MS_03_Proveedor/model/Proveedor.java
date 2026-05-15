package com.example.LogiStock_MS_03_Proveedor.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PROVEEDORES")
@Data
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String contacto;
    private String rubro;
}