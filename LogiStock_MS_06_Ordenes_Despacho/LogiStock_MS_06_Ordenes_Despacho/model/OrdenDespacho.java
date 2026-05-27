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

    @Column(name = "numero_seguimiento", nullable = false, unique = true, length = 50)
    private String numeroSeguimiento; 

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId; 

    @Column(name = "direccion_despacho", nullable = false, length = 255)
    private String direccionDespacho;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDespacho estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoDespacho.PENDIENTE;
        }
    }
}