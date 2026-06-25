package com.example.LogiStock_MS_03_Proveedor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "proveedores",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_proveedor_rut",
                        columnNames = "rut"
                ),
                @UniqueConstraint(
                        name = "uk_proveedor_email",
                        columnNames = "contacto_email"
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "rut",
            nullable = false,
            length = 15
    )
    private String rut;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100
    )
    private String nombre;

    @Column(
            name = "contacto_telefono",
            nullable = false,
            length = 20
    )
    private String contactoTelefono;

    @Column(
            name = "contacto_email",
            nullable = false,
            length = 100
    )
    private String contactoEmail;

    @Column(
            name = "direccion",
            nullable = false,
            length = 200
    )
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "estado",
            nullable = false
    )
    private Estado estado;
}