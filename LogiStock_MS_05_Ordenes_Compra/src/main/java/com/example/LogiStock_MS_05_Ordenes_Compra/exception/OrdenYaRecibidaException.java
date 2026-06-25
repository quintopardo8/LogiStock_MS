package com.example.LogiStock_MS_05_Ordenes_Compra.exception;

public class OrdenYaRecibidaException extends RuntimeException {
     public OrdenYaRecibidaException(Long id) {
        super("Ya se recibió la Orden con el ID: " + id);
    }


}
