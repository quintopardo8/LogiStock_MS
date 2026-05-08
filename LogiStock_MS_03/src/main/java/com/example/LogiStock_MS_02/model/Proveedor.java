package com.example.LogiStock_MS_03.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MS03_PROVEEDORES") 
@Data 
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String contacto;

    private String telefono;
}