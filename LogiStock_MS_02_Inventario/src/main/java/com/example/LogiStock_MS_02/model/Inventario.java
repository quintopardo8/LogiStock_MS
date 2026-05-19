package com.example.LogiStock_MS_02.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productoID", nullable = false)
    private Long productoId;

    @Column(name = "cantidad_reservada", nullable = false)
    private int cantidadReservada;

    @Column(name = "stock_minimo", nullable = false)
    private int stockMinimo;

    @Column(name = "ubicacion", length = 100)
    private String ubicacion;
   

}
