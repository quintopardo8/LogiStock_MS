package com.example.LogiStock_MS_02.dto;


import lombok.Data;

@Data
public class InventarioResponse {

    private Long id;
    private Long productoId;
    private int cantidadDisponible;
    private int cantidadReservada;
    private int stockEfectivo; // Se calcula: Disponible - Reservado
    private int stockMinimo;
    private String ubicacion;

}
