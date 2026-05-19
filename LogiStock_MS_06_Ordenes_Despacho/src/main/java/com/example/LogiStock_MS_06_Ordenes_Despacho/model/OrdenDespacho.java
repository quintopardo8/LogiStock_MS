package com.example.LogiStock_MS_06_Ordenes_Despacho.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_despacho")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenDespacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroSeguimiento; // Código único de rastreo (ej: DESP-10024)

    @Column(nullable = false)
    private Long clienteId; 

    @Column(nullable = false, length = 255)
    private String direccionDespacho;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDespacho estado;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaEnvio;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoDespacho.PENDIENTE;
        }
    }
}