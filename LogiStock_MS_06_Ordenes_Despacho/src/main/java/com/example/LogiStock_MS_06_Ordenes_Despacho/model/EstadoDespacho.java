package com.example.LogiStock_MS_06_Ordenes_Despacho.model;

public enum EstadoDespacho {
    PENDIENTE,   // Creado, pero aún en bodega
    PREPARANDO,  // En proceso de empaque o picking
    DESPACHADO,  // Ya salió en el camión de reparto
    ENTREGADO,   // El cliente recibió todo conforme
    CANCELADO    // Hubo algún problema y se anuló
}